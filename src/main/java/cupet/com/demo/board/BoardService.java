package cupet.com.demo.board;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public List<BoardVO> boardList(Map<String, Object> searchOpations) {
        try {
        	System.out.println("searchOpations in Service : " + searchOpations);
        	int cupet_board_head_no = (int)searchOpations.get("cupet_board_head_no");
        	int searchScopeOptions = (int)searchOpations.get("searchScopeOptions");
        	String searchKeyword = (String)searchOpations.get("searchKeyword");
        	
        	//머릿말 전체, 검색어 없음
        	if (cupet_board_head_no == 4 && (searchKeyword == null || searchKeyword.length() == 0) ) {
        	System.out.println("Board Searvice - 머릿말 전체, 검색어 없음");
        	return boardMapper.getAllBoardList();
        	
        	//머릿말 전체, 검색어 있음-  제목+내용
        	} else if (cupet_board_head_no == 4 && searchScopeOptions == 10 && searchKeyword != null && searchKeyword.length() != 0) { 
            	System.out.println("Board Searvice - 머릿말 전체, 검색어 있음-  제목+내용");
            	return boardMapper.SearchBoardList(searchKeyword);
            	
            //머릿말 전체, 검색어 있음- 제목
        	} else if (cupet_board_head_no == 4 && searchScopeOptions == 20 && searchKeyword != null && searchKeyword.length() != 0) { 
        		System.out.println("Board Searvice - 머릿말 전체, 검색어 있음- 제목");
            	return boardMapper.SearchTitleBoardList(searchKeyword);
            //머릿말 전체, 검색어 있음- 작성자
        	} else if (cupet_board_head_no == 4 && searchScopeOptions == 30 && searchKeyword != null && searchKeyword.length() != 0) { 
        		System.out.println("Board Searvice - 머릿말 전체, 검색어 있음- 작성자");
            	return boardMapper.SearchWriterBoardList(searchKeyword);
            	
            	//머릿말 검색, 검색어 없음
        	} else if (cupet_board_head_no !=4 && (searchKeyword == null || searchKeyword.length() == 0)) { 
        		System.out.println("Board Searvice - 머릿말 검색, 검색어 없음");
            	return boardMapper.HeadSelectBoardList(cupet_board_head_no);
            	
            	//머릿말 검색, 검색어 있음-  제목+내용
        	} else if (searchScopeOptions == 10 && searchKeyword != null && searchKeyword.length() != 0) { 
        		System.out.println("Board Searvice - 머릿말 검색, 검색어 있음-  제목+내용");
            	return boardMapper.SearchHeadBoardList(cupet_board_head_no, searchKeyword);
            	
            	//머릿말 검색, 검색어 있음- 제목
        	} else if (searchScopeOptions == 20 && searchKeyword != null && searchKeyword.length() != 0) { 
        		System.out.println("Board Searvice - 머릿말 검색, 검색어 있음- 제목");
            	return boardMapper.SearchHeadTitleBoardList(cupet_board_head_no, searchKeyword);
            	
            	//머릿말 검색, 검색어 있음- 작성자
        	} else if (searchScopeOptions == 30 && searchKeyword != null && searchKeyword.length() != 0) { 
        		System.out.println("Board Searvice - 머릿말 검색, 검색어 있음- 작성자");
            	return boardMapper.SearchHeadTitleBoardList(cupet_board_head_no, searchKeyword);
        	}	
	
        } catch (Exception e) {	
            System.err.println("Error fetching select options: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return Collections.emptyList();
    }

    public BoardVO boardView(int cupet_board_no) {
        try {
        	int incViewCount = boardMapper.incViewCount(cupet_board_no);
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
        	
        	System.out.println("contentData in Service :"+ contentData);
        	//update한 사람의 user id
        	String cupet_now_user_id = (String)contentData.get("cupet_now_user_id");
        	//글 작성자의 user id
            String cupet_board_no_str = (String) contentData.get("cupet_board_no");
            int cupet_board_no = Integer.parseInt(cupet_board_no_str);
        	BoardVO viewData =  boardMapper.getBoardview(cupet_board_no);
        	String cupet_board_user_id = viewData.getCupet_user_id();
        	
        	System.out.println("현재로그인 아이디 : " + cupet_now_user_id );
        	System.out.println("작성자 아이디 : " + cupet_board_user_id );

        	if (cupet_now_user_id.equals(cupet_board_user_id)) {
        		System.out.println("유저아이디 일치");
        		int status = boardMapper.getBoardupdate(contentData);
        		//성공
        		return status;
        	} else {
        		//실패
        		return 0;
        	}
        
        } catch (Exception e) {
            System.err.println("Error fetching board update: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<BoardVO> recentBoardList() {
        try {
        	List<BoardVO> boardList = boardMapper.getRecentBoard();
        	return boardList;
        } catch (Exception e) {	
            System.err.println("Error fetching select options: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
