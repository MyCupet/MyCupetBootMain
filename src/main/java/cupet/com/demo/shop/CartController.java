package cupet.com.demo.shop;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import cupet.com.demo.dto.PageRequestVO;
import cupet.com.demo.dto.PageResponseVO;
import cupet.com.demo.shop.CartVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api1")
public class CartController {
	
	private final ShopService shopService;
	private final CartService cartService;
	
	@GetMapping("/cart/items")
    public ResponseEntity getCartItems(@CookieValue(value = "token", required = false) String token) {
		
//		if (!jwtService.isValid(token)) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }

        //String cupet_user_id = jwtService.getId(token);
        String cupet_user_id ="kim123";
        List<CartVO> carts = cartService.getList();
        List<Integer> cupet_items = carts.stream().map(CartVO::getCupet_prodno).toList();
        List<ShopVO> shop = shopService.findByIdIn(cupet_items);

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
    
    @PostMapping("/cart/items/{itemId}")
    public ResponseEntity pushCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ) {

        //String cupet_user_id = jwtService.getId(token);
    	String cupet_user_id = "kim123";
        CartVO cart = cartService.findByMemberIdAndItemId(cupet_user_id, cupet_prodno);

        if (cart == null) {
            CartVO newCart = new Cart();
            newCart.setCupet_user_id(cupet_user_id);
            newCart.setCupet_prodno(cupet_prodno);
            cartService.save(newCart);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/cart/items/{itemId}")
    public ResponseEntity removeCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        CartVO cart = cartService.findByMemberIdAndItemId(cupet_user_id, cupet_prodno);

        cartService.delete(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	
}