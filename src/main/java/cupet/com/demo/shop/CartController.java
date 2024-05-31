package cupet.com.demo.shop;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthService;
import cupet.com.demo.dto.PageRequestVO;
import cupet.com.demo.dto.PageResponseVO;
import cupet.com.demo.shop.CartVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value="CartController")
@RequestMapping("/api1/cart")
public class CartController {
	
	private final ShopService shopService;
	private final CartService cartService;
	private final AuthService authService;
	
	
	@PostMapping("/items2")
    public String test(@RequestBody List<String> req) {
		
		System.out.println(req);
		
		return "";
		
		
    }
	
	

	@PostMapping("/items")
    public ResponseEntity getCartItems(@RequestHeader ("Authorization") String jwt, @CookieValue(value = "token", required = false) String token, 
    		PageRequestVO pageRequestVO) throws MyCupetBootMainException {
		Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id =(String) m.get("cupet_user_id");
        //List<CartVO> carts = cartService.findById(cupet_user_id);
        
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
    
//    @PostMapping("/items/{cupet_prodno}")
//    public ResponseEntity pushCartItem(@RequestHeader ("Authorization") String jwt, @PathVariable("cupet_prodno") int cupet_prodno,
//            @CookieValue(value = "token", required = false) String token)throws MyCupetBootMainException {
//
//    	Map<String, Object> m = authService.AuthByUser(jwt);
//        String cupet_user_id =(String) m.get("cupet_user_id");
//        
//        //1. shop이랑 cartproduct를 prodno로 연결
//        
//        
//        //List<CartVO>cart = cartService.findByIdAndProdno(cupet_user_id, cupet_prodno);
//        List<CartVO>cart = cartService.findByProdno(cupet_prodno);
//        System.out.println("cart = " + cart);
//        
//        //id에 할당된 cart_no가 없으면 만들어줌
//        if (cart.isEmpty()) {
//            CartVO newCart = new CartVO();
//            newCart.setCupet_user_id(cupet_user_id);
//            newCart.setCupet_prodno(cupet_prodno);
//            cartRepository.save(newCart);
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    
//    @DeleteMapping("/items/{cupet_prodno}")
//    public ResponseEntity removeCartItem(@RequestHeader ("Authorization") String jwt, @PathVariable("cupet_prodno") int cupet_prodno,
//            @CookieValue(value = "token", required = false) String token)throws MyCupetBootMainException {
//    	Map<String, Object> m = authService.AuthByUser(jwt);
//        String cupet_user_id =(String) m.get("cupet_user_id");
//        
//        CartVO cart = (CartVO) cartService.findByIdAndProdno(cupet_user_id, cupet_prodno);
//        System.out.println("카트 = " + cart);
//
//        cartService.delete(cart);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

	
}