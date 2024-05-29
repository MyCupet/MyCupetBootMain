package cupet.com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cupet.com.demo.entity.ShopVO;
import cupet.com.demo.page.PageRequestVO;

@Mapper
@Repository("shopMapper")
public interface ShopMapper {
	@Select("select * from cupetshop")
	List<ShopVO> getList(PageRequestVO pageRequestVO);
	
	@Select("select count(*) from cupetshop")
	int getTotalCount(PageRequestVO pageRequestVO);
}
