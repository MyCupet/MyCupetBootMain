package cupet.com.demo.board;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("boardMapper")
public interface BoardMapper {
    @Select("SELECT b.*, u.cupet_user_name FROM cupetboard b JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id")
    List<BoardVO> getBoardlist();

    @Select("SELECT b.*, u.cupet_user_name FROM cupetboard b JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id WHERE b.cupet_board_no = #{cupet_board_no}")
    BoardVO getBoardview(int cupet_board_no);
    
    @Delete("DELETE FROM mycupet.cupetboard WHERE cupet_board_no=#{cupet_board_no}")
    BoardVO getBoarddelete(int cupet_board_no);
    
    
    @Insert("INSERT INTO mycupet.cupetboard (cupet_board_title, cupet_board_content, cupet_board_head_no, cupet_user_id) values (#{cupet_board_title}, #{cupet_board_content}, #{cupet_board_head_no}, #{cupet_user_id})")
    BoardVO getBoardinsert(BoardVO boardVO);
    
//    @Update("")
//    BoardVO getBoardupdate(BoardVO board);
    
}
