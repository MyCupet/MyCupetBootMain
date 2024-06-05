package cupet.com.demo.shop;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthService;
import cupet.com.demo.dto.PageRequestVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value="CartController")
@RequestMapping("/api1/cart")
public class CartController {
	
	private final ShopService shopService;
	private final CartService cartService;
	private final AuthService authService;

	@PostMapping("/items")
    public ResponseEntity getCartItems(@RequestHeader ("Authorization") String jwt, @CookieValue(value = "token", required = false) String token, 
    		PageRequestVO pageRequestVO) throws MyCupetBootMainException {
		Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id =(String) m.get("cupet_user_id");
        
        //1 userid를 이용해서 cartno를 가져옴 (cupetuser랑 cupetcart테이블)
        List<CartVO> cart1 = cartService.findByUserId(cupet_user_id); //cart1 = CartVO 형태의 자료가 1개 있음 1:1 userid, cartNo
        
        //2(cartno를 이용해서 cartproductno랑 연결)
        List<Integer> cartt = cart1.stream().map(CartVO::getCupet_cart_no).toList();
        List<CartProdVO> cart2 = cartService.findByCartNo(cartt); //cart2 = cartVO 형태의 여러개가있음 1:N
         
        //3 cartproductno를 이용헤서 shop에있는 prodno를 가져옴
        List<Integer> cart3 = cart2.stream().map(CartProdVO::getCupet_prodno).toList();
        List<ShopVO> cart4 = shopService.findByProdNo(cart3);
        return new ResponseEntity<>(cart4, HttpStatus.OK);
    }
    
    @PostMapping("/items/{cupet_prodno}")
    public ResponseEntity pushCartItem(@RequestHeader ("Authorization") String jwt, @PathVariable("cupet_prodno") int cupet_prodno,
            @CookieValue(value = "token", required = false) String token)throws MyCupetBootMainException {

    	Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id =(String) m.get("cupet_user_id");
        
        //할당된 cartno찾기
        List<CartVO> cart = cartService.findByUserId(cupet_user_id);
        
        //id에 할당된 cart_no가 없으면 만들어줌
        if (cart.isEmpty()) { cartService.newUserAddtoCart(cupet_user_id); }
        List<Integer> cartnumber = cart.stream().map(CartVO::getCupet_cart_no).toList();
        int cart_no = cartnumber.get(0).intValue();

        // cartproduct 테이블에 cartnumber와 shop의 prodno를 넣기
        CartProdVO cartProdVO = new CartProdVO();
        cartProdVO.setCupet_cart_no(cart_no);
        cartProdVO.setCupet_prodno(cupet_prodno);
        cartProdVO.setCupet_cartproduct_amount(1); // 수량 설정, 필요에 따라 변경 가능

        cartService.insert(cartProdVO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/items/{cupet_prodno}")
    public ResponseEntity removeCartItem(@RequestHeader ("Authorization") String jwt, @PathVariable("cupet_prodno") int cupet_prodno,
            @CookieValue(value = "token", required = false) String token)throws MyCupetBootMainException {
    	Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id =(String) m.get("cupet_user_id");
        
        //1. 로그인한 아이디로 cart_no 알아내기
        List<CartVO> cart = cartService.findByUserId(cupet_user_id);
        List<Integer> cartnumber = cart.stream().map(CartVO::getCupet_cart_no).toList();
        int cart_no = cartnumber.get(0).intValue();
        
        //2. cart_no와 axios로 받은 prodno로 cart_produrct_no  알아내기
        List<CartProdVO> cart2 = cartService.findByCartnoAndProdno(cart_no, cupet_prodno);
        List<Integer> cart3 = cart2.stream().map(CartProdVO::getCupet_cartproduct_no).toList();
        int cartprodnum = cart3.get(0).intValue();
        
        //3. cartproductno 가 일치하는 모든 데이터 삭제
        cartService.delete(cartprodnum);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

	
}