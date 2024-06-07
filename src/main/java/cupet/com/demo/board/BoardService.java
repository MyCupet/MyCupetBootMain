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
            int status = boardMapper.Boarddelete(cupet_board_no);
            return status;
        } catch (Exception e) {
            System.err.println("Error fetching board delete: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public int boardInsert(Map<String, Object> contentData) {
        try {
        	int status = boardMapper.Boardinsert(contentData);
            return status;
        } catch (Exception e) {
            System.err.println("Error fetching board insert: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    
    public int boardUpdate(Map<String, Object> contentData) {
        try {
        	//update한 사람의 user id
        	String cupet_now_user_id = (String)contentData.get("cupet_user_id");
        	//글 작성자의 user id
        	String cupet_board_user_id = (boardMapper.getBoardview((int)contentData.get("cupet_board_no"))).cupet_user_id;
        	
        	if (cupet_now_user_id.equals(cupet_board_user_id)) {
        		int status = boardMapper.getBoardupdate(contentData);
        		return status;
        	} else {
        		return 0;
        	}
        
        } catch (Exception e) {
            System.err.println("Error fetching board update: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
}
