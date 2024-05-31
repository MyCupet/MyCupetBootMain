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
	
	
//	@PostMapping("/items2")
//	public void Temptest() throws MyCupetBootMainException {
//		System.out.println("help me : " + jwt);
//		Map<String, Object> m = authService.AuthByUser(jwt);
//		
//	}
	
	@PostMapping("/items")
    public ResponseEntity getCartItems(@RequestHeader ("Authorization") String jwt, @CookieValue(value = "token", required = false) String token, PageRequestVO pageRequestVO) throws MyCupetBootMainException {
		Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id =(String) m.get("cupet_user_id");
        List<CartVO> carts = cartService.findById(cupet_user_id);
        System.out.println("carts = " + carts);
        List<Integer> cupet_cart_itemsProd = carts.stream().map(CartVO::getCupet_prodno).toList();
        System.out.println("cupet_cart_itemsProd = " + cupet_cart_itemsProd);
        List<ShopVO> shop = shopService.findByNo(cupet_cart_itemsProd);
        System.out.println("shop = " + shop);

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
    
//    @PostMapping("/items/{itemId}")
//    public ResponseEntity pushCartItem(
//            @PathVariable("itemId") int itemId,
//            @CookieValue(value = "token", required = false) String token
//    ) {
//
//        //String cupet_user_id = jwtService.getId(token);
//    	String cupet_user_id = "kim123";
//        CartVO cart = cartService.findByMemberIdAndItemId(cupet_user_id, cupet_prodno);
//
//        if (cart == null) {
//            CartVO newCart = new Cart();
//            newCart.setCupet_user_id(cupet_user_id);
//            newCart.setCupet_prodno(cupet_prodno);
//            cartService.save(newCart);
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    
//    @DeleteMapping("/items/{itemId}")
//    public ResponseEntity removeCartItem(
//            @PathVariable("itemId") int itemId,
//            @CookieValue(value = "token", required = false) String token
//    ) {
//
//        if (!authService.AuthByUser(jwt)) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }
//
//        int memberId = jwtService.getId(token);
//        CartVO cart = cartService.findByMemberIdAndItemId(cupet_user_id, cupet_prodno);
//
//        cartService.delete(cart);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

	
}