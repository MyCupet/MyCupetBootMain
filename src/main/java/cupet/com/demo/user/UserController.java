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
         result = authService.AuthByUser(token);
      } catch (MyCupetBootMainException e) {
         result.put("error", e.getMessage());
         result.put("status", false);
         e.printStackTrace();
      }
//      UserVO user = userService.userView(String.valueOf(result.get("cupet_user_id")));
//      result.put("user", user);
//      result.put("status", true);
      return result;
   }
}