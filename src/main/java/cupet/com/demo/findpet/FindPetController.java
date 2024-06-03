package cupet.com.demo.findpet;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthService;
import cupet.com.demo.dto.CupetUserAddressVO;
import cupet.com.demo.dto.MissingPetVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api1/findpet")
@RequiredArgsConstructor
public class FindPetController implements MyCupetDbInterface {

	private final AuthService authService;
	private final FindPetService findPetService;
	@PostMapping("/getmissingpet")
	public void GetMissingPet(@RequestParam("token") String token) throws MyCupetBootMainException {
		Map<String , Object> temp = authService.AuthByUser(token);
		String id = String.valueOf(temp.get(memberid));
		System.out.println(id);

	}
	
	@GetMapping("/getUserLocate")
	public Object userGetLocate(@RequestHeader ("Authorization") String token) {
		System.out.println("유저로케이트 접근");
		
		Map<String, Object> res = new HashMap<>();
		Map<String, Object> temp;
		try {
			temp = authService.AuthByUser(token);
		} catch (MyCupetBootMainException e) {
			e.printStackTrace();
			res.put("msg", "failedToken");
			return res; 
		
		}
		String id = String.valueOf(temp.get(memberid));
		System.out.println(id);
		CupetUserAddressVO rescua = findPetService.userGetLocate(id);
		if(rescua != null) {
			res.put("UserLocate", rescua);
			res.put("msg", "success");
		}
		else {
			res.put("msg", "failed");
		}
		System.out.println("res : " +res);
		return res;

	}
	
	@PostMapping("/misiinPetInsert")
	public Object misiinPetInsert(@RequestHeader ("Authorization") String token,@RequestBody MissingPetVO obj) {
		Map<String, Object> res = new HashMap<>();
		Map<String , Object> usr;
		
		System.out.println("잃어버린 동물 인서트");
		try {
			Map<String , Object> temp = authService.AuthByUser(token);
			usr = temp;
		} catch (MyCupetBootMainException e) {
			res.put("result", "notoken");
			return res; 
		}
		int A = Integer.valueOf(obj.getReward());
		int B = Integer.valueOf((String)usr.get("cupet_user_point"));
		
		if(A>B) {
			res.put("result", "morepoint");
			return res;
		}
		
		int num = findPetService.insertMissingPet((String)usr.get("cupet_user_id"),obj);
		if(1==num) {
			res.put("result", "success");
			findPetService.findPetInsertPointDec(usr,B-A);
			return res;
		}else if(-1==num) {
			res.put("result", "exist");
			return res;
		}
		else {
			res.put("result", "failed");
			return res;
		}
		
	}
	
	
}
