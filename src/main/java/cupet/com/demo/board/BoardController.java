package cupet.com.demo.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cupet.com.demo.board.selectoption.SelectoptionService;
import cupet.com.demo.board.selectoption.SelectoptionVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api1")
public class BoardController {
    private final SelectoptionService selectoptionService;
    private final BoardService boardService;
    
    @GetMapping("/selectoptionList")
    @ResponseBody
    public Map<String, Object> selectoptionList(Model model) {
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
            // 예외가 발생한 경우에는 에러 메시지와 상태를 전달합니다.
            result.put("error", e.getMessage());
            result.put("status", false);
            e.printStackTrace();
        }
        return result;
    }
}
