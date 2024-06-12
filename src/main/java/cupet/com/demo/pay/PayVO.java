package cupet.com.demo.pay;

import lombok.Data;

@Data
public class PayVO {
   int cupet_payno;
   int cupet_pay_price;
   String cupet_user_id; 
   String cupet_payment_uid;
   String cupet_pay_date;
}