package cupet.com.demo.user.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetVO {
	public String cupet_pet_no;
	public String cupet_pet_name;
	public String cupet_user_id;
	public String cupet_pet_birth;
	public String cupet_pet_type;
}
