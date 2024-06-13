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
public class ShopService {
	private final ShopMapper shopMapper;
	
	public PageResponseVO<ShopVO> getList(PageRequestVO pageRequestVO) {
    	List<ShopVO> list = shopMapper.getList(pageRequestVO);
    	int total = shopMapper.getTotalCount(pageRequestVO);

    	return new PageResponseVO<>(list ,total, pageRequestVO.getSize(), pageRequestVO.getPageNo());
    	}
	
	public List<ShopVO> findByProdNo(List<Integer> cupet_prodno) {
	    int[] prodnos = cupet_prodno.stream().mapToInt(Integer::intValue).toArray();
	    List<ShopVO> k = shopMapper.findByNo(prodnos);
	    return k;
	}
	
	public ShopVO findByProdNo2(int cupet_prodno) {
	    return shopMapper.findByProdNo2(cupet_prodno);
	}
	
	public void decreaseProductCount(int prodno, int orderCount) {
        shopMapper.updateProductCount(prodno, orderCount);
    }
	
	public int insert(ShopVO shopVO) {
		return shopMapper.insert(shopVO);
	}
}
