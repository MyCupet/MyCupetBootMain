package cupet.com.demo.shop;

import lombok.Data;

@Data
public class CartProdVO {

	private int cupet_cartprouct_no; //주문항목 고유번호(PK)
	private int cupet_cart_no; //장바구니 고유번호
	private int cupet_prodno; //상품 고유번호
	private int cupet_cartprouct_amount; //수량
	
}
