package cupet.com.demo.user;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    
    @Update("UPDATE cupetuseraddress SET roadAddress = #{address.roadAddress}, jibunAddress = #{address.jibunAddress}, detailAddress = #{address.detailAddress} " +
            "WHERE cupet_user_id = #{address.cupet_user_id}")
    void updateUserAddress(@Param("address") UserAddressVO userAddressVO);
    
    @Select("SELECT * FROM cupetuseraddress WHERE cupet_user_id = #{cupet_user_id}")
    UserAddressVO viewUserAddress(@Param("cupet_user_id") String cupet_user_id);
    
    @Delete("DELETE FROM cupetuser WHERE cupet_user_id = #{cupet_user_id}")
    void deleteUser(@Param("cupet_user_id") String cupet_user_id);
    
    @Select("select * from cupetuser")
    List<UserVO> getAllUsers();
    
    @Select("select * from cupetuser where cupet_user_id = #{cupet_user_id}")
    UserVO getUserById(@Param("cupet_user_id") String cupet_user_id);
}