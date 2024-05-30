package cupet.com.demo.shop;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cupet.com.demo.dto.PageRequestVO;

@Mapper
@Repository("cartMapper")
public interface CartMapper {
	
	@Select("select * from cupetcart")
	List<CartVO> getCartList(PageRequestVO pageRequestVO);
	
	@Select("select * from cupetcart where cupet_user_id = #{cupet_user_id}")
	List<CartVO> findById(String cupet_user_id);
	
	@Select("select count(*) from cupetcart")
	int getCartTotalCount(PageRequestVO pageRequestVO);
	
	@Insert("insert into cupetcart shopVO")
	int insert(CartVO cartVO);
	
	@Delete("delete cupet_prod_no from cupetcart where cupet_produ_no = ${cupet_prod_no} ")
	int delete(CartVO cartVO);
	
	
}