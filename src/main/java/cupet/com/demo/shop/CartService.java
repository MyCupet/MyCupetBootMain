package cupet.com.demo.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cupet.com.demo.dto.PageRequestVO;
import cupet.com.demo.dto.PageResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
	private final CartMapper cartMapper;
	
	public List<CartProdVO> getCartProd(int cartnum) {
		return cartMapper.getCartProd(cartnum);
	}
	 
	public List<CartVO> findByUserId(String cupet_user_id) {
		return cartMapper.findByUserId(cupet_user_id);
	}
	
	public List<CartVO> findByUserIdAndProd(String cupet_user_id) {
		return cartMapper.findByUserId(cupet_user_id);
	}
	
	public List<CartProdVO> findByCartNo(List<Integer> cartt) {
	    return cartMapper.findByCartNo(cartt);
	}
	
	public List<CartProdVO> findByCartNo2(int cartNo) {
		return cartMapper.findByCartNo2(cartNo);
	}
	
	//새 cart를 유저에게 할당
	public int newUserAddtoCart(String cupet_user_id) {
        CartVO newCart = new CartVO();
        newCart.setCupet_user_id(cupet_user_id);
        return cartMapper.newUserAddtoCart(newCart);
    }
	
	public List<CartProdVO> findById(String cupet_user_id) {
		return cartMapper.findById(cupet_user_id);
	}
	
	public List<CartVO> findByProdno(int cupet_prodno) {
		return cartMapper.findByProdno(cupet_prodno);
	}
	
	public int insert(CartProdVO cartProd) {
		 return cartMapper.insert(cartProd);
	}
	
	public List<CartVO> findByIdAndProdno(String id, int prodno) {
		return cartMapper.findByIdAndProdno(id, prodno);
	}
	
	public int delete(int cartprodnum) {
		return cartMapper.delete(cartprodnum);
	}
	
	//장바구니 전체 삭제
	public int deleteCartAll(int cartno) {
		return cartMapper.deleteCartAll(cartno);
	}
	
	public List<CartProdVO>findByCartnoAndProdno(int cart_no, int cupet_prodno) {
		return cartMapper.findByCartnoAndProdno(cart_no, cupet_prodno);
	}
	
	public int updateQuantity(int cartprodnum, int newQuantity) {
	    return cartMapper.updateQuantity(cartprodnum, newQuantity);
	}

}
