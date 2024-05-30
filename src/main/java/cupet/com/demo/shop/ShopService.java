package cupet.com.demo.shop;

import java.util.List;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import cupet.com.demo.entity.ShopVO;
import cupet.com.demo.mapper.ShopMapper;
import cupet.com.demo.page.PageRequestVO;
import cupet.com.demo.page.PageResponseVO;

=======
import cupet.com.demo.dto.PageRequestVO;
import cupet.com.demo.dto.PageResponseVO;
>>>>>>> 823f15513ea3e7d8e2ee26a7e20e3a968a1c680c
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
	}
<<<<<<< HEAD

=======
>>>>>>> 823f15513ea3e7d8e2ee26a7e20e3a968a1c680c
