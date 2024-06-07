package cupet.com.demo.shop;

import java.util.List;

import org.springframework.stereotype.Service;

import cupet.com.demo.user.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
	private final OrderMapper orderMapper;
	
	public List<OrderVO> findByUserId(String cupet_user_id) {
		return orderMapper.findByUserId(cupet_user_id);
	}
	
	public int insert(OrderVO orderVO) {
		 return orderMapper.insert(orderVO);
	}
	
	public UserVO findUserById(String cupet_user_id){
		return orderMapper.findUserById(cupet_user_id);
	}
	
	public int payPoint(int afterPoint, String cupet_user_id) {
		 return orderMapper.payPoint(afterPoint, cupet_user_id);
	}
	
	public int getOrderNo() {
		 return orderMapper.getOrderNo();
	}
	
	public void insertDetail(OrderProdVO orderProdVO) {
        orderMapper.insertDetail(orderProdVO);
    }

}
