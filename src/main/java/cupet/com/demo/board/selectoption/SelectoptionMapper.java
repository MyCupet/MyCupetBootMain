package cupet.com.demo.board.selectoption;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("selectoptionMapper")
public interface SelectoptionMapper {

	@Select("select * from cupetboard_head")
	List<SelectoptionVO> getSelectoptionlist();

}