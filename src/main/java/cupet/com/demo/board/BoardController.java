package cupet.com.demo.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
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
import cupet.com.demo.board.selectoption.SelectoptionSearchVO;
import cupet.com.demo.board.selectoption.SelectoptionService;
import cupet.com.demo.board.selectoption.SelectoptionVO;
import cupet.com.demo.user.pet.PetVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api1")
public class BoardController {
    private final SelectoptionService selectoptionService;
    private final BoardService boardService;
	private final AuthService authService;

  
    @GetMapping("/selectOptionList")
    @ResponseBody
    public Map<String, Object> selectoptionList() throws MyCupetBootMainException {
        System.out.println("셀렉트 옵션 리스트 추출");
      
        Map<String, Object> result = new HashMap<>();
        try {
            List<SelectoptionVO> options = selectoptionService.selectoptionList();
            result.put("list", options);
            result.put("status", true);
        } catch (Exception e) {
            // 예외가 발생한 경우에는 에러 메시지와 상태를 전달합니다.
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return result;
    }
    
    @GetMapping("/selectedOptionSearch")
    @ResponseBody
    public Map<String, Object> selectoptionSearch() throws MyCupetBootMainException {
        System.out.println("셀렉트 옵션 서치 추출");
      
        Map<String, Object> result = new HashMap<>();
        try {
            List<SelectoptionSearchVO> selectedOptionSearch = selectoptionService.selectoptionSearch();
            result.put("list", selectedOptionSearch);
            result.put("status", true);
            System.out.println("selectedOptionSearch : " + result);
        } catch (Exception e) {
            // 예외가 발생한 경우에는 에러 메시지와 상태를 전달합니다.
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return result;
    }
    
    @PostMapping("/boardList")
    @ResponseBody
    public Map<String, Object> boardList(@RequestBody Map<String, Object> searchOpations) {
        System.out.println("보드 리스트 추출");
        Map<String, Object> result = new HashMap<>();
        
        Map<String, Object> params = (Map<String, Object>) searchOpations.get("params");
        System.out.println("params" + params);

        try {
        		
                Map<String, Object> SearchOptions = new HashMap<>();
                int cupet_board_head_no = (int)params.get("selectedOptionList");
                int searchScopeOptions = (int)params.get("selectedOptionSearch");
                String searchKeyword = (String)params.get("searchQuery");

                SearchOptions.put("cupet_board_head_no", cupet_board_head_no);
                SearchOptions.put("searchScopeOptions", searchScopeOptions);
                SearchOptions.put("searchKeyword", searchKeyword);

                System.out.println("검색 조건 - 머릿말번호 : " + cupet_board_head_no);
                System.out.println("검색 조건 - 검색 범위번호 : " + searchScopeOptions);
                System.out.println("검색 조건 - 검색어 : " + searchKeyword);
                
                List<BoardVO> list = boardService.boardList(SearchOptions);

                
                result.put("list", list);
                result.put("status", true);
                System.out.println("result : " + result);
                return result;                
            
        } catch (Exception e) {
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return result;

    }
    

    @GetMapping("/boardView")
    @ResponseBody
    public Map<String, Object> boardView(@RequestParam("cupet_board_no") int cupet_board_no) {
        System.out.println("보드 상세보기 추출");
        Map<String, Object> result = new HashMap<>();
        try {
            BoardVO board = boardService.boardView(cupet_board_no);
            result.put("board", board);
            result.put("status", true);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return result;
    }
    
    @GetMapping("/boardDelete")
    @ResponseBody
    public String boardDelete(@RequestParam("cupet_board_no") int cupet_board_no) {
        System.out.println("보드 삭제");
        Map<String, Object> result = new HashMap<>();
        try {
            int status = boardService.boardDelete(cupet_board_no);
            result.put("status", true);
            
            if (status != 0 ) {
            	return "성공";
            } else {
            	return "실패";
            }
       
        } catch (Exception e) {
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return "실패";
    }
    
    @PostMapping("/boardInsert")
    @ResponseBody
    public String boardInsert(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> contentData) {
        System.out.println("보드 insert");
        Map<String, Object> userMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();



        try {
        	userMap = authService.AuthByUser(token);
        	String cupet_user_id = (String)userMap.get("cupet_user_id");
        	System.out.println(contentData);
        	
        	contentData.put("cupet_user_id", cupet_user_id);
        	int status = boardService.boardInsert(contentData);
            result.put("status", true);
        	
        	if (status != 0 ) {
            	return "성공";
            } else {
            	return "실패";
            }
       
        } catch (Exception e) {
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return "실패";
    }
    
    
    @PostMapping("/boardUpdate")
    @ResponseBody
    public String boardUpdate(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> contentData) {
        System.out.println("보드 update");
        Map<String, Object> userMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();



        try {
        	userMap = authService.AuthByUser(token);
        	String cupet_user_id = (String)userMap.get("cupet_user_id");
        	System.out.println(contentData);
        	
        	contentData.put("cupet_user_id", cupet_user_id);
        	int status = boardService.boardUpdate(contentData);
            result.put("status", true);
        	
        	if (status != 0 ) {
            	return "성공";
            } else {
            	return "실패";
            }
       
        } catch (Exception e) {
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return "실패";
    }
    
    @GetMapping("/recentBoardView")
	@ResponseBody
	public Map<String, Object> recentBoardView() {
		System.out.println("최근 게시물 5개 추출");
		Map<String, Object> result = new HashMap<>();
		try {
			List<BoardVO> recentBoardView = boardService.recentBoardList();
			List<Integer> recentBoardNoList = recentBoardView.stream().map(BoardVO::getCupet_board_no).collect(Collectors.toList());
			result.put("cupet_board_no", recentBoardNoList);
			result.put("recentBoardView", recentBoardView);
			result.put("status", true);
		} catch (Exception e) {
			result.put("error", e.getMessage());
			result.put("status", false);
			e.printStackTrace();
		}
		return result;
	}
}
