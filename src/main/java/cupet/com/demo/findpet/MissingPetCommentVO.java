package cupet.com.demo.findpet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MissingPetCommentVO {

	String comment_no;
	String cupet_pet_no;
	String comment;
	String cupet_user_id;
	String cupet_user_nickname;
}
