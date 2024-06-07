package cupet.com.demo.shop;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cupet.com.demo.user.UserVO;

@Mapper
@Repository("orderMapper")
public interface OrderMapper {
	
	@Select("select * from cupetorder where cupet_user_id = #{cupet_user_id}")
	List<OrderVO> findByUserId(String cupet_user_id);
	
	@Select("select * from cupetuser where cupet_user_id = #{cupet_user_id}")
	UserVO findUserById(String cupet_user_id);
	
	@Insert("INSERT INTO cupetorder (cupet_user_id, cupet_receiver_name, cupet_receiver_add, cupet_receiver_phone, cupet_total_price, cupet_order_date) "
	        + "VALUES (#{cupet_user_id}, #{cupet_receiver_name}, #{cupet_receiver_add}, #{cupet_receiver_phone}, #{cupet_total_price}, #{cupet_order_date})")
	@Options(keyProperty = "cupet_order_no")
	int insert(OrderVO orderVO);
	
	@Update("update cupetuser set cupet_user_point = #{arg0} where cupet_user_id = #{arg1}")
	int payPoint(int afterPoint, String cupet_user_id);
	
	@Select("select cupet_order_no from cupetorder order by cupet_order_no desc LIMIT 1 ")
	int getOrderNo();
	
	@Insert("INSERT INTO cupetorderproduct (cupet_order_no, cupet_prodno, cupet_orderprice, cupet_orderprodcnt) " +
            "VALUES (#{cupet_order_no}, #{cupet_prodno}, #{cupet_orderprice}, #{cupet_orderprodcnt})")
	@Options(keyProperty = "cupet_orderprodno")
    void insertDetail(OrderProdVO orderProdVO);

	
}
