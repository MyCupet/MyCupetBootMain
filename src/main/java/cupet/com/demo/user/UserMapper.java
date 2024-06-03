package cupet.com.demo.user;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("userMapper")
public interface UserMapper {
    @Update("UPDATE cupetuser SET cupet_user_nickname = #{user.cupet_user_nickname}, " +
            "cupet_user_name = #{user.cupet_user_name}, " +
            "cupet_user_address = #{user.cupet_user_address}, " +
            "cupet_user_phonenumber = #{user.cupet_user_phonenumber}, " +
            "cupet_user_birth = #{user.cupet_user_birth}, " +
            "cupet_user_gender = #{user.cupet_user_gender} " +
            "WHERE cupet_user_id = #{user.cupet_user_id}")
    void updateUser(@Param("user") UserVO userVO);
    
    @Delete("DELETE FROM cupetuser WHERE cupet_user_id = #{cupet_user_id}")
    void deleteUser(@Param("cupet_user_id") String cupet_user_id);
}