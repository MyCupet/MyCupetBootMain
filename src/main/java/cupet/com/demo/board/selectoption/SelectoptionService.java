package cupet.com.demo.board.selectoption;

import java.util.List;

import org.springframework.stereotype.Service;

import cupet.com.demo.board.BoardSelectoptionVO;
import cupet.com.demo.board.SelectoptionMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectoptionService {

 private final SelectoptionMapper selectoptionMapper;

 public List<BoardSelectoptionVO> selectoptionList() {
     try {
         return selectoptionMapper.getSelectoptionlist();
     } catch (Exception e) {
         // 예외를 로깅하고 다시 던집니다.
         System.err.println("Error fetching select options: " + e.getMessage());
         e.printStackTrace();
         throw e;
     }
 }
}