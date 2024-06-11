package cupet.com.demo.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MissingPetVO {
	String petName;
	String birth;
	String locateX;
	String locateY;
	String features;
	String reward;
	
	
	String cupet_pet_no;
	String cupet_user_id;
	
	
	public double nodeDistance(String nx, String ny) {
		
		final int R = 6371;
		if(locateX==null||locateY==null) {
			return -9999;
		}
		
		double x2 = Double.parseDouble(this.locateX);
		double y2 = Double.parseDouble(this.locateY);
		
		double nx2 = Double.parseDouble(nx);
		double ny2 = Double.parseDouble(ny);
		
		double latDistance = Math.toRadians(nx2 - x2);
        double lonDistance = Math.toRadians(ny2 - y2);
	
		
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(x2)) * Math.cos(Math.toRadians(nx2))
                + Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return  R * c;

	}
}
