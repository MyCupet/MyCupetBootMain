package cupet.com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cupet.com.demo.vo.cupetboard_selectoptionVO;

@Mapper
@Repository("selectoptionMapper")
public interface SelectoptionMapper {

	@Select("select * from cupetboard_head")
	List<cupetboard_selectoptionVO> getSelectoptionlist();

}
