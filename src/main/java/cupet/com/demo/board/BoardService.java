package cupet.com.demo.board;

import java.util.List;
import java.util.Map;

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
            System.err.println("Error fetching select options: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public BoardVO boardView(int cupet_board_no) {
        try {
            return boardMapper.getBoardview(cupet_board_no);
        } catch (Exception e) {
            System.err.println("Error fetching board view: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public int boardDelete(int cupet_board_no) {
        try {
            int status = boardMapper.getBoarddelete(cupet_board_no);
            return status;
        } catch (Exception e) {
            System.err.println("Error fetching board delete: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public int boardInsert(Map<String, Object> contentData) {
        try {
        	int status = boardMapper.getBoardinsert(contentData);
            return status;
        } catch (Exception e) {
            System.err.println("Error fetching board insert: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
