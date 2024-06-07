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
    @Select("SELECT b.*, u.cupet_user_name,cupet_user_nickname FROM cupetboard b JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id")
    List<BoardVO> getBoardlist();

    @Select("SELECT b.*, u.cupet_user_name, cupet_user_nickname FROM cupetboard b JOIN cupetuser u ON b.cupet_user_id = u.cupet_user_id WHERE b.cupet_board_no = #{cupet_board_no}")
    BoardVO getBoardview(int cupet_board_no);
    
    @Delete("DELETE FROM cupetboard WHERE cupet_board_no=#{cupet_board_no}")
    int Boarddelete(int cupet_board_no);
    
    
    @Insert("INSERT INTO cupetboard (cupet_board_title, cupet_board_content, cupet_board_head_no, cupet_user_id) values (#{cupet_board_title}, #{cupet_board_content}, #{cupet_board_head_no}, #{cupet_user_id})")
    int Boardinsert(Map<String, Object> contentData);
    
    @Update("UPDATE cupetboard SET cupet_board_title=#{cupet_board_title}, cupet_board_content=#{cupet_board_content}, cupet_board_head_no=#{cupet_board_head_no} WHERE cupet_board_no=#{cupet_board_no} ")
    int getBoardupdate(Map<String, Object> contentData);
    
}
