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
	List<CartVO> findByUserId(String cupet_user_id);
	
	@Select({
	    "<script>",
	    "select * from cupetcartproduct where cupet_cart_no in",
	    "<foreach item='item' index='index' collection='cartt' open='(' separator=',' close=')'>",
	    "#{item}",
	    "</foreach>",
	    "</script>"
	})
	List<CartProdVO> findByCartNo(@Param("cartt") List<Integer> cartt);
	
	
	@Insert("insert into cupetcart (cupet_user_id) values (#{cupet_user_id})"
			)
	int newUserAddtoCart(String cupet_user_id);
	
	
	
	
	@Select("select * from cupetcart where cupet_user_id = #{cupet_user_id}")
	List<CartProdVO> findById(String cupet_user_id);

	@Select("select * from cupetshop where cupet_prodno = #{cupet_prodno}")
	List<CartVO> findByProdno(int cupet_prodno);
	
//	@Select("select * from cupetshop where cupet_user_id = #{arg0} and cupet_prodno = #{arg1}")
//	List<CartVO> findByIdAndProdno(String cupet_user_id, int cupet_prodno);
	
	@Select("select count(*) from cupetcart")
	int getCartTotalCount(PageRequestVO pageRequestVO);
	
	@Insert("insert into cupetcart shopVO")
	int insert(CartVO cartVO);
	
	@Delete("delete cupet_prod_no from cupetcart where cupet_prod_no = #{cupet_prod_no} ")
	int delete(CartVO cartVO);
	
	
}