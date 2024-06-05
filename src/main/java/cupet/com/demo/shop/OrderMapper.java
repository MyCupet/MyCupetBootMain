package cupet.com.demo.shop;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("orderMapper")
public interface OrderMapper {
	
	@Select("select * from cupetorder where cupet_user_id = #{cupet_user_id}")
	List<OrderVO> findByUserId(String cupet_user_id);
	
	@Insert("INSERT INTO cupetorder (cupet_user_id, cupet_receiver_name, cupet_receiver_add, cupet_receiver_phone, cupet_total_price, cupet_order_date) "
	        + "VALUES (#{cupet_user_id}, #{cupet_receiver_name}, #{cupet_receiver_add}, #{cupet_receiver_phone}, #{cupet_total_price}, #{cupet_order_date})")
	@Options(keyProperty = "cupet_order_no")
	int insert(OrderVO orderVO);

	
}
