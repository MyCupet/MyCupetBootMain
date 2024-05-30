package cupet.com.demo.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {

	private String cupet_user_id;
	private String cupet_userpwd;
	private String cupet_user_name;
	private String cupet_user_nickname;
	private String cupet_user_address;
	private String cupet_user_gender;
	private String cupet_user_phonenumber;
	private String cupet_user_age;
	private int cupet_user_point;
	private String cupet_user_principle;

}
