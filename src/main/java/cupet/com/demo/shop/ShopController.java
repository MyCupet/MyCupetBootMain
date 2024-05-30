package cupet.com.demo.shop;

<<<<<<< HEAD
import cupet.com.demo.entity.ShopVO;
import cupet.com.demo.mapper.ShopMapper;
import cupet.com.demo.page.PageRequestVO;
import cupet.com.demo.page.PageResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cupet.com.demo.page.PageRequestVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
=======
import cupet.com.demo.dto.PageRequestVO;
import cupet.com.demo.dto.PageResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

>>>>>>> 823f15513ea3e7d8e2ee26a7e20e3a968a1c680c
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD

import java.util.List;

@Slf4j
=======
>>>>>>> 823f15513ea3e7d8e2ee26a7e20e3a968a1c680c
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
    
    
	
<<<<<<< HEAD
}
=======
}
>>>>>>> 823f15513ea3e7d8e2ee26a7e20e3a968a1c680c
