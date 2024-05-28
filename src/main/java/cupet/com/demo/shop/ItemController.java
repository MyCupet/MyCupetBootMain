package cupet.com.demo.shop;

import cupet.com.demo.Repository.ProdRepository;
import cupet.com.demo.entity.cupetshopproductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ProdRepository prodRepository;

    @GetMapping("/api/items")
    public List <cupetshopproductVO> getItems(){
        List<cupetshopproductVO> cupetshopproduct = prodRepository.findAll();
        return cupetshopproduct;
    }
}
