package cupet.com.demo.shop;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthService;
import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final AuthService authService;
    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping("/api1/order")
    public ResponseEntity getOrder(@RequestHeader("Authorization") String jwt, @CookieValue(value = "token", required = false) String token) throws MyCupetBootMainException {

        Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id = (String) m.get("cupet_user_id");
        System.out.println(cupet_user_id);
        List<OrderVO> orders = orderService.findByUserId(cupet_user_id);
        System.out.println("SAdfadfadfsf = " + orders);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/api1/order")
    public ResponseEntity pushOrder(@RequestHeader("Authorization") String jwt, @RequestBody OrderDto dto, @CookieValue(value = "token", required = false) String token) throws MyCupetBootMainException {

        Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id = (String) m.get("cupet_user_id");

        // 새로운 주문서 작성
        OrderVO newOrder = new OrderVO();
        newOrder.setCupet_user_id(cupet_user_id);
        newOrder.setCupet_receiver_name(dto.getName());
        newOrder.setCupet_receiver_add(dto.getAddress());
        newOrder.setCupet_receiver_phone(dto.getPhone());
        newOrder.setCupet_total_price(dto.getPrice()); 
        newOrder.setCupet_order_date(dto.getDate()); 

        orderService.insert(newOrder);
        System.out.println(newOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
