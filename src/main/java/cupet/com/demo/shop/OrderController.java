package cupet.com.demo.shop;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthService;
import cupet.com.demo.user.UserVO;
import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final AuthService authService;
    private final OrderService orderService;
    private final CartService cartService;
    private final ShopService shopService;

    @GetMapping("/api1/order")
    public ResponseEntity getOrder(@RequestHeader("Authorization") String jwt, @CookieValue(value = "token", required = false) String token) throws MyCupetBootMainException {

        Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id = (String) m.get("cupet_user_id");
        List<OrderVO> orders = orderService.findByUserId(cupet_user_id);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/api1/order")
    public ResponseEntity pushOrder(@RequestHeader("Authorization") String jwt, @RequestBody OrderDto dto, @CookieValue(value = "token", required = false) String token) throws MyCupetBootMainException {

        Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id = (String) m.get("cupet_user_id");

        // 새로운 주문서 작성
        OrderVO newOrder = new OrderVO();
        newOrder.setCupet_user_id(cupet_user_id);
        newOrder.setCupet_receiver_name(dto.getName());
        newOrder.setCupet_receiver_add(dto.getAddress());
        newOrder.setCupet_receiver_phone(dto.getPhone());
        newOrder.setCupet_total_price(dto.getPrice()); 
        newOrder.setCupet_order_date(dto.getDate()); 

        orderService.insert(newOrder);
        System.out.println(newOrder);
        
        //orderproduct테이블에 추가하기
        //1. order_no 가져오기 -> 가장 마지막에 추가된 data
        int order_no = orderService.getOrderNo();
        		
        //2. prodno 가져오기
        List<CartVO> cart1 = cartService.findByUserId(cupet_user_id);
        List<Integer> cartt = cart1.stream().map(CartVO::getCupet_cart_no).toList();
        List<CartProdVO> cart2 = cartService.findByCartNo(cartt); 
        System.out.println("cart2 = " + cart2);
        List<Integer> prodnoList = cart2.stream().map(CartProdVO::getCupet_prodno).toList();
        
        //3. orderprice가져오기
        List<ShopVO> price1 = shopService.findByProdNo(prodnoList);
        List<Integer> priceList = price1.stream().map(ShopVO::getCupet_prodprice).toList();
        
        //4. oprderprodcnt 가져오기
        List<Integer> prodcntList = cart2.stream().map(CartProdVO::getCupet_cartprodcnt).toList();
        
        // OrderProdVO에 데이터 삽입
        for (int i = 0; i < cart2.size(); i++) {
            OrderProdVO orderProdVO = new OrderProdVO();
            orderProdVO.setCupet_order_no(order_no);
            orderProdVO.setCupet_prodno(cart2.get(i).getCupet_prodno());
            orderProdVO.setCupet_orderprice(priceList.get(i));
            orderProdVO.setCupet_orderprodcnt(prodcntList.get(i));
            orderService.insertDetail(orderProdVO);
        }
        
        //주문이 완료되면 기존에 장바구니에 있던 품목들 삭제
        List<CartVO> cart = cartService.findByUserId(cupet_user_id); 
        List<Integer> cartnumber = cart.stream().map(CartVO::getCupet_cart_no).toList();
        int cartno = cartnumber.get(0).intValue();
        cartService.deleteCartAll(cartno);
        
        //포인트 까기
        UserVO user1 = orderService.findUserById(cupet_user_id);
        int beforePoint = Integer.parseInt(user1.getCupet_user_point());
        int orderPoint = dto.getPrice();
        int afterPoint = beforePoint - orderPoint;
        orderService.payPoint(afterPoint, cupet_user_id);
        
        Map<Integer, Integer> prodCntMap = cart2.stream().collect(Collectors.toMap(CartProdVO::getCupet_prodno, CartProdVO::getCupet_cartprodcnt));
        
        //아이템 수량 줄이기
        for (Map.Entry<Integer, Integer> entry : prodCntMap.entrySet()) {
            int prodno = entry.getKey();
            int orderCount = entry.getValue();
            shopService.decreaseProductCount(prodno, orderCount);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/api1/order/{cupet_order_no}")
    public ResponseEntity<OrderVO> OrderDetailView (@RequestHeader("Authorization") String jwt,  @PathVariable("cupet_order_no") int cupet_order_no,
            @CookieValue(value = "token", required = false) String token) throws MyCupetBootMainException {

        Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id = (String) m.get("cupet_user_id");

        OrderVO orderDetail = orderService.findOrderDetailByOrderNo(cupet_order_no, cupet_user_id);

        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }
    
    @PostMapping("/api1/order/items")
    public ResponseEntity<List<ShopVO>> getOrderItems(@RequestHeader("Authorization") String jwt, @RequestParam("cupet_order_no") int cupet_order_no, 
        @CookieValue(value = "token", required = false) String token) throws MyCupetBootMainException {
        Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id = (String) m.get("cupet_user_id");

        List<Integer> productNos = orderService.findProductNosByOrderNo(cupet_order_no);
        System.out.println("productNos = " + productNos);

        List<ShopVO> products = shopService.findByProdNo(productNos);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @GetMapping("/api1/user/point")
    public int getUserPoint (@RequestHeader("Authorization") String jwt, @CookieValue(value = "token", required = false) String token) throws MyCupetBootMainException {
    	Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id = (String) m.get("cupet_user_id");
        
        UserVO userinfo = orderService.findUserById(cupet_user_id);
        int userPoint = Integer.parseInt(userinfo.getCupet_user_point());
        System.out.println("userPoint = " + userPoint);
        return userPoint;
    }
}
