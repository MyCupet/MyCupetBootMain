package cupet.com.demo.shop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {
   @GetMapping("/api/items")
        public List <String> getItems(){
            List<String> items = new ArrayList<>();
            items.add("강아지사료");
            items.add("고양이사료");
            items.add("햄스터사료");
            items.add("강아지장난감");
            items.add("고양이장난감");
            items.add("햄스터장난감");

            return items;
        }
}