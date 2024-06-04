package cupet.com.demo.shop;


import lombok.Data;

@Data
public class ShopVO {

    private int cupet_prodno; //상품 고유번호(PK)
    private String cupet_prodname;
    private int cupet_prodprice;
    private String cupet_prodcont;
    private String cupet_prodimgpath;
    private int cupet_proddiscountper;
}
