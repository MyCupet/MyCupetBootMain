package cupet.com.demo.user;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {
   @Select("SELECT * FROM cupetuser WHERE cupet_user_id = #{cupet_user_id}")
    UserVO getUserview(String cupet_user_id);
}