package cupet.com.demo.pay;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PayMapper {
    @Insert("insert into cupetpay (cupet_user_id, cupet_pay_price, cupet_payment_uid, cupet_pay_date) VALUES (#{cupet_user_id}, #{cupet_pay_price}, #{cupet_payment_uid}, #{cupet_pay_date})")
    @Options(useGeneratedKeys = true, keyProperty = "cupet_payno")
    int insert(PayVO payVO);

    @Select("select * from cupetpay where cupet_payno = #{cupet_payno}")
    List<PayVO> findPayById(int cupet_payno);
    
    @Update("update cupetuser set cupet_user_point = #{arg0} where cupet_user_id = #{arg1}")
	int chargePoint(int afterPoint, String cupet_user_id);
    
    @Select("select * from cupetpay where cupet_user_id = #{cupet_user_id}")
    List<PayVO> findPaymentsByUserId(String cupet_user_id);
}
