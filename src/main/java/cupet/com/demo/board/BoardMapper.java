package cupet.com.demo.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("boardMapper")
public interface BoardMapper {
    // 머릿말 전체, 검색어 없음
    @Select("SELECT B.*, he.* " +
            "FROM (SELECT b.*, u.cupet_user_name, u.cupet_user_nickname " +
            "      FROM cupetboard b " +
            "      JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id) AS B " +
            "JOIN cupetboard_head he ON B.cupet_board_head_no = he.cupet_board_head_no " +
            "ORDER BY B.cupet_board_no DESC")
    List<BoardVO> getAllBoardList();
    
    // 머릿말 전체, 검색어 있음 - 제목 + 내용
    @Select("SELECT B.*, he.* " +
            "FROM (SELECT b.*, u.cupet_user_name, u.cupet_user_nickname " +
            "      FROM cupetboard b " +
            "      JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id " +
            "      WHERE strip_html_tags(b.cupet_board_title) LIKE CONCAT('%', #{searchKeyword}, '%') " +
            "         OR strip_html_tags(b.cupet_board_content) LIKE CONCAT('%', #{searchKeyword}, '%')) AS B " +
            "JOIN cupetboard_head he ON B.cupet_board_head_no = he.cupet_board_head_no " +
            "ORDER BY B.cupet_board_no DESC")
    List<BoardVO> SearchBoardList(String searchKeyword);
    
    // 머릿말 전체, 검색어 있음 - 제목
    @Select("SELECT B.*, he.* " +
            "FROM (SELECT b.*, u.cupet_user_name, u.cupet_user_nickname " +
            "      FROM cupetboard b " +
            "      JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id " +
            "      WHERE strip_html_tags(b.cupet_board_title) LIKE CONCAT('%', #{searchKeyword}, '%')) AS B " +
            "JOIN cupetboard_head he ON B.cupet_board_head_no = he.cupet_board_head_no " +
            "ORDER BY B.cupet_board_no DESC")
    List<BoardVO> SearchTitleBoardList(String searchKeyword);
    
    // 머릿말 전체, 검색어 있음 - 작성자
    @Select("SELECT B.*, he.* " +
            "FROM (SELECT b.*, u.cupet_user_name, u.cupet_user_nickname " +
            "      FROM cupetboard b " +
            "      JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id " +
            "      WHERE strip_html_tags(u.cupet_user_nickname) LIKE CONCAT('%', #{searchKeyword}, '%')) AS B " +
            "JOIN cupetboard_head he ON B.cupet_board_head_no = he.cupet_board_head_no " +
            "ORDER BY B.cupet_board_no DESC")
    List<BoardVO> SearchWriterBoardList(String searchKeyword);
    
    // 머릿말 검색, 검색어 없음
    @Select("SELECT B.*, he.* " +
            "FROM (SELECT b.*, u.cupet_user_name, u.cupet_user_nickname " +
            "      FROM cupetboard b " +
            "      JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id) AS B " +
            "JOIN cupetboard_head he ON B.cupet_board_head_no = he.cupet_board_head_no " +
            "WHERE B.cupet_board_head_no=#{cupet_board_head_no} " +
            "ORDER BY B.cupet_board_no DESC")
    List<BoardVO> HeadSelectBoardList(int cupet_board_head_no);
    
    // 머릿말 검색, 검색어 있음 - 제목 + 내용
    @Select("SELECT B.*, he.* " +
            "FROM (SELECT b.*, u.cupet_user_name, u.cupet_user_nickname " +
            "      FROM cupetboard b " +
            "      JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id " +
            "      WHERE strip_html_tags(b.cupet_board_title) LIKE CONCAT('%', #{searchKeyword}, '%') " +
            "         OR strip_html_tags(b.cupet_board_content) LIKE CONCAT('%', #{searchKeyword}, '%')) AS B " +
            "JOIN cupetboard_head he ON B.cupet_board_head_no = he.cupet_board_head_no " +
            "WHERE B.cupet_board_head_no=#{cupet_board_head_no} " +
            "ORDER BY B.cupet_board_no DESC")
    List<BoardVO> SearchHeadBoardList(int cupet_board_head_no, String searchKeyword);
    
    // 머릿말 검색, 검색어 있음 - 제목
    @Select("SELECT B.*, he.* " +
            "FROM (SELECT b.*, u.cupet_user_name, u.cupet_user_nickname " +
            "      FROM cupetboard b " +
            "      JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id " +
            "      WHERE strip_html_tags(b.cupet_board_title) LIKE CONCAT('%', #{searchKeyword}, '%')) AS B " +
            "JOIN cupetboard_head he ON B.cupet_board_head_no = he.cupet_board_head_no " +
            "WHERE B.cupet_board_head_no=#{cupet_board_head_no} " +
            "ORDER BY B.cupet_board_no DESC")
    List<BoardVO> SearchHeadTitleBoardList(int cupet_board_head_no, String searchKeyword);
    
    // 머릿말 검색, 검색어 있음 - 작성자
    @Select("SELECT B.*, he.* " +
            "FROM (SELECT b.*, u.cupet_user_name, u.cupet_user_nickname " +
            "      FROM cupetboard b " +
            "      JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id " +
            "      WHERE strip_html_tags(u.cupet_user_nickname) LIKE CONCAT('%', #{searchKeyword}, '%')) AS B " +
            "JOIN cupetboard_head he ON B.cupet_board_head_no = he.cupet_board_head_no " +
            "WHERE B.cupet_board_head_no=#{cupet_board_head_no} " +
            "ORDER BY B.cupet_board_no DESC")
    List<BoardVO> SearchHeadWriterBoardList(int cupet_board_head_no, String searchKeyword);
    
    // view
    @Select("SELECT b.*, u.cupet_user_name, u.cupet_user_nickname, he.* " +
            "FROM cupetboard b " +       
            "JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id " +
            "JOIN cupetboard_head he ON b.cupet_board_head_no = he.cupet_board_head_no " +
            "WHERE b.cupet_board_no = #{cupet_board_no}")
    BoardVO getBoardview(int cupet_board_no);

    @Update("UPDATE cupetboard SET CUPET_BOARD_VIEWCNT = CUPET_BOARD_VIEWCNT + 1 WHERE CUPET_BOARD_NO = #{cupet_board_no}")
    int incViewCount(int cupet_board_no);
    
    // delete
    @Delete("DELETE FROM cupetboard WHERE cupet_board_no = #{cupet_board_no}")
    int Boarddelete(int cupet_board_no);
    
    // insert
    @Insert("INSERT INTO cupetboard (cupet_board_title, cupet_board_content, cupet_board_head_no, cupet_user_id) " +
            "VALUES (#{cupet_board_title}, #{cupet_board_content}, #{cupet_board_head_no}, #{cupet_user_id})")
    int Boardinsert(Map<String, Object> contentData);
    
    // update
    @Update("UPDATE cupetboard SET cupet_board_title = #{cupet_board_title}, cupet_board_content = #{cupet_board_content}, cupet_board_head_no = #{cupet_board_head_no} " +
            "WHERE cupet_board_no = #{cupet_board_no}")
    int getBoardupdate(Map<String, Object> contentData);
    

    // 최근 게시물 5개 불러오기
    @Select("SELECT b.*, u.cupet_user_nickname, cbh.cupet_board_head_name \r\n"
    		+ "FROM cupetboard b \r\n"
    		+ "JOIN cupetuser u \r\n"
    		+ "ON b.cupet_user_id = u.cupet_user_id \r\n"
    		+ "JOIN cupetboard_head cbh\r\n"
    		+ "ON b.cupet_board_head_no = cbh.cupet_board_head_no \r\n"
    		+ "ORDER BY b.cupet_board_regdate DESC \r\n"
    		+ "LIMIT 5")
    List<BoardVO> getRecentBoard();
}

