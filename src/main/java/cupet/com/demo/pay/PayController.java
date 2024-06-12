package cupet.com.demo.pay;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthService;
import cupet.com.demo.shop.OrderService;
import cupet.com.demo.user.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api1/pay")
public class PayController {
	
	private final AuthService authService;
    private final PayService payService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity createPayment(@RequestHeader("Authorization") String jwt, @RequestBody PayDto dto) throws MyCupetBootMainException {
    	Map<String, Object> m = authService.AuthByUser(jwt);
        String cupet_user_id = (String) m.get("cupet_user_id");
        System.out.println(cupet_user_id);
        System.out.println("PayDto: " + dto.toString()); // DTO 객체 로깅
        
        PayVO newPay = new PayVO();
        newPay.setCupet_user_id(cupet_user_id);
        newPay.setCupet_pay_price(dto.getCupet_pay_price());
        newPay.setCupet_payment_uid(dto.getCupet_payment_uid());
        
        payService.insert(newPay);
        System.out.println(newPay);
        
        //결제 완료 시 포인트 충전
        UserVO user1 = orderService.findUserById(cupet_user_id);
        int beforePoint = Integer.parseInt(user1.getCupet_user_point());
        int orderPoint = dto.getCupet_pay_price();
        int afterPoint = beforePoint + orderPoint;
        payService.chargePoint(afterPoint, cupet_user_id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{cupet_payno}")
    public CustomResponse<PayVO> getPayment(@PathVariable int cupet_payno) {
        PayVO payVO = (PayVO) payService.findPayById(cupet_payno);
        return CustomResponse.ok("결제 정보를 성공적으로 조회했습니다.", payVO);
    }


}
