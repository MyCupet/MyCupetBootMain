package cupet.com.demo.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthService;
import cupet.com.demo.user.pet.PetService;
import cupet.com.demo.user.pet.PetVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api1")
public class UserController {
	private final AuthService authService;
	private final PetService petService;
	private final UserService userService;
	
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
	
	@GetMapping("/petView")
	@ResponseBody
	public Map<String, Object> petView(@RequestParam("cupet_user_id") String cupet_user_id) {
	    System.out.println("애완동물 목록 추출");
	    Map<String, Object> result = new HashMap<>();
	    try {
	        List<PetVO> petView = petService.petView(cupet_user_id);
	        result.put("petView", petView);
	        result.put("status", true);
	    } catch (Exception e) {
	        result.put("error", e.getMessage());
	        result.put("status", false);
	        e.printStackTrace();
	    }
	    return result;
	}
	
	@PostMapping("/petInsert")
    @ResponseBody
    public Map<String, Object> petInsert(@RequestBody PetVO petVO) {
        System.out.println("펫 정보 삽입 요청 받음");
        Map<String, Object> result = new HashMap<>();
        try {
            // 펫 정보 삽입 서비스 호출
            PetVO insertedPet = petService.petInsert(petVO);
            result.put("pet", insertedPet);
            result.put("status", true);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return result;
    }
	
	@PostMapping("/petUpdate")
	@ResponseBody
	public Map<String, Object> petUpdate(@RequestBody PetVO petVO) {
	    System.out.println("펫 정보 수정 요청 받음");
	    Map<String, Object> result = new HashMap<>();
	    try {
	        // 펫 정보 수정 서비스 호출
	        PetVO updatedPet = petService.petUpdate(petVO);
	        result.put("pet", updatedPet);
	        result.put("status", true);
	    } catch (Exception e) {
	        result.put("error", e.getMessage());
	        result.put("status", false);
	        e.printStackTrace();
	    }
	    return result;
	}
	
	@GetMapping("/petDelete")
	@ResponseBody
	public Map<String, Object> petDelete(@RequestParam("cupet_pet_no") int cupet_pet_no) {
	    System.out.println("애완동물 삭제 요청");
	    Map<String, Object> result = new HashMap<>();
	    try {
	        int petDelete = petService.petDelete(cupet_pet_no);
	        result.put("petDelete", petDelete);
	        result.put("status", true);
	    } catch (Exception e) {
	        result.put("error", e.getMessage());
	        result.put("status", false);
	        e.printStackTrace();
	    }
	    return result;
	}
	
	@PostMapping("/userUpdate")
	@ResponseBody
	public Map<String, Object> userUpdate(@RequestBody UserVO userVO) {
	    System.out.println("사용자 정보 수정 요청 받음");
	    Map<String, Object> result = new HashMap<>();
	    try {
	        // 펫 정보 수정 서비스 호출
	        UserVO updatedUser = userService.userUpdate(userVO);
	        result.put("user", updatedUser);
	        result.put("status", true);
	    } catch (Exception e) {
	        result.put("error", e.getMessage());
	        result.put("status", false);
	        e.printStackTrace();
	    }
	    return result;
	}
}