package cupet.com.demo.shop;

import java.util.HashMap;
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
        
       //아이템 수량 줄이기
        
        
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
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
