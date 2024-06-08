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
	
	@Insert("insert into cupetorder (cupet_user_id, cupet_receiver_name, cupet_receiver_add, cupet_receiver_phone, cupet_total_price, cupet_order_date) "
	        + "values (#{cupet_user_id}, #{cupet_receiver_name}, #{cupet_receiver_add}, #{cupet_receiver_phone}, #{cupet_total_price}, #{cupet_order_date})")
	@Options(keyProperty = "cupet_order_no")
	int insert(OrderVO orderVO);
	
	@Update("update cupetuser set cupet_user_point = #{arg0} where cupet_user_id = #{arg1}")
	int payPoint(int afterPoint, String cupet_user_id);
	
	//가장 마지막에 추가된 order no가져오기
	@Select("select cupet_order_no from cupetorder order by cupet_order_no desc LIMIT 1 ")
	int getOrderNo();
	
	@Insert("insert into cupetorderproduct (cupet_order_no, cupet_prodno, cupet_orderprice, cupet_orderprodcnt) " +
            "values (#{cupet_order_no}, #{cupet_prodno}, #{cupet_orderprice}, #{cupet_orderprodcnt})")
	@Options(keyProperty = "cupet_orderprodno")
    void insertDetail(OrderProdVO orderProdVO);

	@Select("select * from cupetorder where cupet_order_no = #{orderNo} and cupet_user_id = #{userId}")
    OrderVO selectOrderDetailByOrderNo(@Param("orderNo") int orderNo, @Param("userId") String userId);
}