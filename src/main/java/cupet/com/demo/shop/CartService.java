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
	
	public PageResponseVO<CartVO> getList(PageRequestVO pageRequestVO) {
    	List<CartVO> list = cartMapper.getCartList(pageRequestVO);
    	int total = cartMapper.getCartTotalCount(pageRequestVO);

    	return new PageResponseVO<CartVO>(list ,total, pageRequestVO.getSize(), pageRequestVO.getPageNo());
    	}
	 
	public List<CartVO> findByUserId(String cupet_user_id) {
		return cartMapper.findByUserId(cupet_user_id);
	}
	
	public List<CartProdVO> findByCartNo(List<Integer> cartt) {
	    return cartMapper.findByCartNo(cartt);
	}
	
//	public List<CartVO> findById(String cupet_user_id) {
//		return cartMapper.findById(cupet_user_id);
//	}
	public List<CartProdVO> findById(String cupet_user_id) {
		return cartMapper.findById(cupet_user_id);
	}
	
	
//	public List<CartVO> findByIdAndProdno(@Param("cupet_user_id")String cupet_user_id, @Param("cupet_prodno")int cupet_prodno) {
//		return cartMapper.findByIdAndProdno(cupet_user_id, cupet_prodno);
//	}
	
	public int insert(CartVO cart) {
		return cartMapper.insert(cart);
	}
	
//	public int delete(CartVO cart)  {
//		return cartMapper.delete(cart);
//	}
	
}
