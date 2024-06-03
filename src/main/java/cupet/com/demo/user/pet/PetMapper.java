package cupet.com.demo.user.pet;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("petMapper")
public interface PetMapper {
	@Select("SELECT * FROM cupetpet WHERE cupet_user_id = #{cupet_user_id}")
	List<PetVO> getAllPets(@Param("cupet_user_id") String cupet_user_id);
    
    @Insert("INSERT INTO cupetpet(cupet_pet_name, cupet_pet_birth, cupet_pet_type, cupet_user_id) VALUES (#{cupet_pet_name}, #{cupet_pet_birth}, #{cupet_pet_type}, #{cupet_user_id})")
    void insertPet(PetVO petVO);
    
    @Update("UPDATE cupetpet\r\n"
    		+ "SET cupet_pet_name = #{cupet_pet_name},\r\n"
    		+ "    cupet_pet_birth = #{cupet_pet_birth},\r\n"
    		+ "    cupet_pet_type = #{cupet_pet_type}\r\n"
    		+ "WHERE cupet_user_id = #{cupet_user_id}\r\n"
    		+ "  AND cupet_pet_no = #{cupet_pet_no} ")
    void updatePet(PetVO petVO);
    
    @Delete("DELETE FROM cupetpet WHERE cupet_pet_no = #{cupet_pet_no}")
    void deletePet(@Param("cupet_pet_no") int cupet_pet_no);
}