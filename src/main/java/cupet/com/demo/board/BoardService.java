package cupet.com.demo.board;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	 private final BoardMapper boardMapper;

	 public List<BoardVO> boardList() {
	     try {
	         return boardMapper.getBoardlist();
	     } catch (Exception e) {
	         // 예외를 로깅하고 다시 던집니다.
	         System.err.println("Error fetching select options: " + e.getMessage());
	         e.printStackTrace();
	         throw e;
	     }
	 }
}
