package cupet.com.demo.user.pet;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("petMapper")
public interface PetMapper {
	@Select("SELECT * FROM cupetpet WHERE cupet_user_id = #{cupet_user_id}")
    PetVO getPetview(@Param("cupet_user_id") String cupet_user_id);
    
    @Insert("INSERT INTO cupetpet(cupet_pet_name, cupet_pet_birth, cupet_pet_type, cupet_user_id) VALUES (#{cupet_pet_name}, #{cupet_pet_birth}, #{cupet_pet_type}, #{cupet_user_id})")
    void insertPet(PetVO petVO);
}