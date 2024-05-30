package cupet.com.demo.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cupet.com.demo.MyCupetBootMainException;
import cupet.com.demo.auth.AuthProvider;
import cupet.com.demo.auth.AuthService;
import cupet.com.demo.board.selectoption.SelectoptionService;
import cupet.com.demo.board.selectoption.SelectoptionVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api1")
public class BoardController {
    private final SelectoptionService selectoptionService;
    private final BoardService boardService;
    private final AuthService as;
    @GetMapping("/selectoptionList")
    @ResponseBody
    public Map<String, Object> selectoptionList(Model model, @RequestHeader("Authorization") String token) throws MyCupetBootMainException {
        System.out.println("셀렉트 옵션 리스트 추출");
        System.out.println(as.AuthByUser(token));
      
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
    
    @GetMapping("/boardList")
    @ResponseBody
    public Map<String, Object> boardList(Model model) {
        System.out.println("보드 리스트 추출");
        Map<String, Object> result = new HashMap<>();
        try {
            List<BoardVO> options = boardService.boardList();
            result.put("list", options);
            result.put("status", true);
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
}
