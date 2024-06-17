package cupet.com.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CupetUserAddressVO {
	String cupet_user_id;
	String roadAddress;
	String jibunAddress;
	String detailAddress;
	String locateX;
	String locateY;
}
