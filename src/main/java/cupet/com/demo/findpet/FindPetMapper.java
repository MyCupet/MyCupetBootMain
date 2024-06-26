package cupet.com.demo.findpet;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cupet.com.demo.dto.CupetUserAddressVO;
import cupet.com.demo.dto.MissingPetVO;
import cupet.com.demo.user.UserVO;


@Mapper
@Repository("findPetMapper")
public interface FindPetMapper {
	@Select("select * from cupetuseraddress where cupet_user_id = #{cupet_user_id}")
	CupetUserAddressVO getUserLocate(String id);



	@Insert("insert into cupetpet_missing (cupet_pet_no,locateX,locateY,birth,features,reward,cupet_user_id) "
			+ "values (#{cupet_pet_no}, #{locateX},#{locateY},#{birth},#{features},#{reward},#{cupet_user_id})")
	int insertMissingPet(Object setCupet_user_id);



	@Update("UPDATE cupetuser SET cupet_user_point = #{cupet_user_point}"
			+ "WHERE cupet_user_id = #{cupet_user_id}")
	int findPetInsertPointDec(UserVO user);



	@Select("select * from cupetpet_missing where cupet_user_id = #{cupet_user_id} ")
	List<MissingPetVO> getAllMissingPet(MissingPetVO ms);



	@Select("select * from cupetpet_missing")
	List<MissingPetVO> getMarkerList();



	@Select("SELECT cp.*, cm.*, cu.cupet_user_name, cu.cupet_user_nickname " +
	        "FROM cupetpet cp " +
	        "INNER JOIN cupetpet_missing cm ON cp.cupet_pet_no = cm.cupet_pet_no " +
	        "INNER JOIN cupetuser cu ON cp.cupet_user_id = cu.cupet_user_id " +
	        "WHERE cp.cupet_pet_no = #{petno}")
	Map<String, Object> getPetDetailInfo(String petno);




	@Insert("insert into cupetpet_missing_comment (cupet_pet_no, comment , cupet_user_id,cupet_user_nickname) values ("
			+ "#{cupet_pet_no},#{comment},#{cupet_user_id},#{cupet_user_nickname})")
	int addComment(MissingPetCommentVO mpc);

	@Select("select * from cupetpet_missing_comment where comment = #{comment}")
	MissingPetCommentVO getMisssingPetCommentDetail(String comment);

	@Select("select * from cupetpet_missing_comment where cupet_pet_no = #{cupet_pet_no}")
	List<MissingPetCommentVO> getMisssingPetComments(String petNo);

	@Delete("DELETE FROM cupetpet_missing_comment WHERE comment_no = #{comment_no}")
    void deleteComment(@Param("comment_no") String comment_no);
}
