package cupet.com.demo.pay;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PayService {
    private final PayMapper payMapper;

	public int insert(PayVO payVO) {
		 return payMapper.insert(payVO);
	}

    public List<PayVO> findPayById(int cupet_payno) {
        return payMapper.findPayById(cupet_payno);
    }
    
    public int chargePoint(int afterPoint, String cupet_user_id) {
		 return payMapper.chargePoint(afterPoint, cupet_user_id);
	}
    
    public List<PayVO> findPaymentsByUserId(String cupet_user_id) {
        return payMapper.findPaymentsByUserId(cupet_user_id);
    }
    
}  
