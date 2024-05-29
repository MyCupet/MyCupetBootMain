package cupet.com.demo.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("boardMapper")
public interface BoardMapper {
	@Select("select *, u.cupet_user_name from cupetboard b join cupetuser u where b.cupet_user_id = u.cupet_user_id")
	List<BoardVO> getBoardlist();
}
