package cupet.com.demo.shop;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.dto.PageRequestVO;
import cupet.com.demo.dto.PageResponseVO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController(value="ShopController")
@RequestMapping("/api1")
public class ShopController {
	private final ShopService shopService;
	
    @GetMapping("/products")
    public PageResponseVO<ShopVO> getItems(PageRequestVO pageRequestVO){
    	PageResponseVO<ShopVO> pageResponseVO = shopService.getList(pageRequestVO);
    	return pageResponseVO;
    }
	
    
    @GetMapping("/shop/{cupet_prodno}")
    public ResponseEntity<ShopVO> shopDetail(@PathVariable("cupet_prodno") int cupet_prodno, @CookieValue(value = "token", required = false) String token) throws MyCupetBootMainException {

        ShopVO prodinfo = shopService.findByProdNo2(cupet_prodno);
        System.out.println("cupet_prodno = " + cupet_prodno);
        System.out.println(prodinfo);
        return new ResponseEntity<>(prodinfo, HttpStatus.OK);
    }
}