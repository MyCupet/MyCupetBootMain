package cupet.com.demo.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MissingPetVO {
	String petName;
	String birth;
	String locateX;
	String locateY;
	String features;
	String reward;
	
	
	String cupet_pet_no;
	String cupet_user_id;
}
