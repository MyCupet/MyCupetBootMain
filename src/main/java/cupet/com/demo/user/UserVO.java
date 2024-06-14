package cupet.com.demo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	public String cupet_user_id;
	public String cupet_userpwd;
	public String cupet_user_name;
	public String cupet_user_nickname;
	public String cupet_user_address;
	public String cupet_user_gender;
	public String cupet_user_phonenumber;
	public String cupet_user_point;
	public String cupet_user_principle;
	public String cupet_user_birth;
	public String cupet_user_email;
}