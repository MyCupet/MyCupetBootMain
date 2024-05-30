package cupet.com.demo.shop;

import java.util.List;

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

    	log.info("cart_list {} ", list);
    	log.info("cart_total = {} ", total);

    	return new PageResponseVO<CartVO>(list ,total, pageRequestVO.getSize(), pageRequestVO.getPageNo());
    	}
	 
	public List<CartVO> findById(String cupet_user_id) {
		return cartMapper.findById(cupet_user_id);
	}
	
//	public int insert(CartVO cart) {
//		return cartMapper.insert(cart);
//	}
//	
//	public int delete(CartVO cart)  {
//		return cartMapper.delete(cart);
//	}
	
}
