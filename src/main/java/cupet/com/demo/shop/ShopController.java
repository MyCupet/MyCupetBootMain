package cupet.com.demo.shop;

import cupet.com.demo.dto.PageRequestVO;
import cupet.com.demo.dto.PageResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api1")
public class ShopController {
	private final ShopService shopService;
	
    @GetMapping("/products")
    public PageResponseVO<ShopVO> getItems(PageRequestVO pageRequestVO){
    	PageResponseVO<ShopVO> pageResponseVO = shopService.getList(pageRequestVO);
    	return pageResponseVO;
    }
    
    
	
}