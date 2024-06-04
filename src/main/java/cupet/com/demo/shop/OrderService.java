package cupet.com.demo.shop;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
