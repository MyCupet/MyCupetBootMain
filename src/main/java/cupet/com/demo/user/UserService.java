package cupet.com.demo.user;

import java.util.List;

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
	
	public UserAddressVO userAddressUpdate(UserAddressVO userAddressVO) {
        try {
            userMapper.updateUserAddress(userAddressVO);
            return userAddressVO;
        } catch (Exception e) {
            System.err.println("Error updating user address: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public UserAddressVO userAddressView(String cupet_user_id) {
        try {
            return userMapper.viewUserAddress(cupet_user_id);
        } catch (Exception e) {
            System.err.println("Error viewing user address: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
	
	public String userDelete(String cupet_user_id) {
        try {
            userMapper.deleteUser(cupet_user_id);
            return cupet_user_id;
        } catch (Exception e) {
            System.err.println("Error deleting pet: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
	
	public List<UserVO> getAllUsers() {
        return userMapper.getAllUsers();
    }
	
	public UserVO getUserById(String cupet_user_id) {
        return userMapper.getUserById(cupet_user_id);
    }
}