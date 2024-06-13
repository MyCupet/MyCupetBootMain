package cupet.com.demo.shop;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.dto.PageRequestVO;
import cupet.com.demo.dto.PageResponseVO;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/shop/add")
    public ResponseEntity<Map<String, Object>> addProduct(@RequestBody ShopVO shopVO, @CookieValue(value = "token", required = false) String token) {
        int savedProduct = shopService.insert(shopVO);
        int cupetProdno = shopVO.getCupet_prodno(); // auto-increment된 ID 가져오기

        Map<String, Object> response = new HashMap<>();
        response.put("cupet_prodno", cupetProdno);
        response.put("message", "상품 등록 성공");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
