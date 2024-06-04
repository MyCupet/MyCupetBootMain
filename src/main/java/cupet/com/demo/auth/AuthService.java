package cupet.com.demo.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cupet.com.demo.MyCupetBootMainException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthProvider authProvider;

	public Map<String, Object> AuthByUser(String token) throws MyCupetBootMainException {
		if (token == null) {
			throw new MyCupetBootMainException(ErrorCode.NOT_FIND_CUPETLOFINUSERTOKEN);
		}

		Map<String, Object> response = authProvider.getAuthenticationByData(token);
		System.out.println(response);
		return response;
	}

}