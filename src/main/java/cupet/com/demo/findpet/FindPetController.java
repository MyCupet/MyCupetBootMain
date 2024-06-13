package cupet.com.demo.findpet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
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
	
	
	
	//유저의 위치정보를 가져오는 컨트롤러 
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
		//유저의 위치정보를 가져오는 메서드
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
	
	@PostMapping("/getMarkerList")
	public Map<String, Object> missingPetinfoList(@RequestBody Map<String,Object> obj) {
	    System.out.println(obj);
	    
	    @SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) obj.get("params");
	    
	    String lat = String.valueOf(params.get("lat"));
	    String lng = String.valueOf(params.get("lng"));
	    
	    System.out.println(lat);
	    System.out.println(lng);
	    
	    Map<String, Object> response = new HashMap<>();
	    List<MissingPetVO> list = findPetService.getMarkerList(lat, lng);

	    response.put("markerList", list);
	    
	    System.out.println(response);
	    return response;
	}

	
	@PostMapping("/getPetDetailInfo")
	public Map<String, Object> getPetDetailInfo(
	    @RequestBody Map<String,String> obj) {
		Map<String,Object> response  = findPetService.getPetDetailInfo(obj.get("petNo"));
	    return response;
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
	
	@PostMapping("/addComment")
	public String addComment(@RequestBody Map<String,String> body,@RequestHeader ("Authorization") String token) throws MyCupetBootMainException {
		String content = body.get("content");
		String petNo = body.get("cupetPetNo");
		Map<String, Object> temp = authService.AuthByUser(token);
		String id = (String) temp.get("cupet_user_id");
		String nickname = (String) temp.get("cupet_user_nickname");
		String num = findPetService.addComment(id,nickname,content,petNo);
		return num;
	}
	
	@PostMapping("/MisssingPetComments")
	public Map<String , Object> MisssingPetComments(@RequestBody Map<String,String> body){
		
		String petNo = body.get("cupetPetNo");
		System.out.println(petNo +": 댓글리스트 출력");
		Map<String , Object> res = new HashMap<>();
		List<MissingPetCommentVO> list = findPetService.getMisssingPetComments(petNo);
		res.put("list", list);
		return res;
	}
	
	@PostMapping("/commentDelete")
	@ResponseBody
	public Map<String, Object> commentDelete(@RequestBody Map<String, String> request) {
		System.out.println("댓글 삭제 요청");
		String comment_no = request.get("comment_no");
		Map<String, Object> result = new HashMap<>();
		try {
			String deleteComment = findPetService.commentDelete(comment_no);
			result.put("deleteComment", deleteComment);
			result.put("status", true);
		} catch (Exception e) {
			result.put("error", e.getMessage());
			result.put("status", false);
			e.printStackTrace();
		}
		return result;
	}
	
}
