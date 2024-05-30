package cupet.com.demo.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cupet.com.demo.dto.PageRequestVO;

@Mapper
@Repository("shopMapper")
public interface ShopMapper {
	@Select("select * from cupetshop")
	List<ShopVO> getList(PageRequestVO pageRequestVO);
	
	@Select("select count(*) from cupetshop")
	int getTotalCount(PageRequestVO pageRequestVO);
}
