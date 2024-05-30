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

    	log.info("list {} ", list);
    	log.info("total = {} ", total);

    	return new PageResponseVO<>(list ,total, pageRequestVO.getSize(), pageRequestVO.getPageNo());
    	}
	
	public List<ShopVO> findByNo(List<Integer> cupet_prodno) {
		return shopMapper.findByNo(cupet_prodno);
	}

	}
