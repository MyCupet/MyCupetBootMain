package cupet.com.demo.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserMapper userMapper;
	
	public UserVO userUpdate(UserVO userVO) {
        try {
            userMapper.updateUser(userVO);
            return userVO;
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}