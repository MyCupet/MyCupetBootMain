package cupet.com.demo.shop;

import lombok.Data;

@Data
public class OrderVO {
	int cupet_order_no;
	String cupet_user_id;
	int cupet_total_price;
	String cupet_receiver_name;
	String cupet_receiver_add;
	String cupet_receiver_phone;
	int cupet_order_itemcnt;
}
