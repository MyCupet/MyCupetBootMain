package cupet.com.demo.board.selectoption;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectoptionService {

 private final SelectoptionMapper selectoptionMapper;

 public List<SelectoptionVO> selectoptionList() {
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