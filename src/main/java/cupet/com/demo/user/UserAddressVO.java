package cupet.com.demo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddressVO {
	String cupet_user_id;
	String roadAddress;
	String jibunAddress;
	String detailAddress;
	String locateX;
	String locateY;
}