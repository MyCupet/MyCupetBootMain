package cupet.com.demo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api1")
public class UserController {
//   private final UserService userService;
	private final AuthService authService;

	@PostMapping("/userView")
	@ResponseBody
	public Map<String, Object> userView(@RequestHeader("Authorization") String token) {
		System.out.println("사용자 상세보기 추출");
		Map<String, Object> result = new HashMap<>();
		try {
			// 토큰을 사용하여 사용자 정보 인증 및 추출
			result = authService.AuthByUser(token);
			// 상태 추가
			result.put("status", true);
		} catch (MyCupetBootMainException e) {
			// 오류 처리
			result.put("error", e.getMessage());
			result.put("status", false);
			e.printStackTrace();
		}
		return result;
	}
}