package cupet.com.demo.shop;

import lombok.*;

@Data
public class OrderDto {
    private String name;
    private String postcode;
    private String address;
    private String phone;
    private int price;
    private String date; 
}