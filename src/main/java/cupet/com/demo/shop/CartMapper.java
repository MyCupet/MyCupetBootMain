package cupet.com.demo.shop;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cupet.com.demo.dto.PageRequestVO;

@Mapper
@Repository("cartMapper")
public interface CartMapper {
	
	//전체 불러오기
	@Select("select * from cupetcart")
	List<CartVO> getCartList(PageRequestVO pageRequestVO);
	
	//할당된 cartno불러오기
	@Select("select * from cupetcart where cupet_user_id = #{cupet_user_id}")
	List<CartVO> findByUserId(String cupet_user_id);
	
	//새 카트 할당
	@Insert("insert into cupetcart (cupet_user_id) values (#{cupet_user_id})")
    @Options(useGeneratedKeys = true, keyProperty = "cupet_cart_no")
    int newUserAddtoCart(CartVO cart);
	
	//cartno를 이용해서 cartproduct_no랑 연결
	@Select({"<script>","select * from cupetcartproduct where cupet_cart_no in",
		"<foreach item='item' index='index' collection='cartt' open='(' separator=',' close=')'>","#{item}","</foreach>","</script>"})
	List<CartProdVO> findByCartNo(@Param("cartt") List<Integer> cartt);

	@Select("select * from cupetcart where cupet_user_id = #{cupet_user_id}")
	List<CartProdVO> findById(String cupet_user_id);

	@Select("select * from cupetshop where cupet_prodno = #{cupet_prodno}")
	List<CartVO> findByProdno(int cupet_prodno);
	
	@Select("select * from cupetshop where cupet_user_id = #{arg0} and cupet_prodno = #{arg1}")
	List<CartVO> findByIdAndProdno(String cupet_user_id, int cupet_prodno);
	
	@Select("select count(*) from cupetcart")
	int getCartTotalCount(PageRequestVO pageRequestVO);

	@Insert("INSERT INTO cupetcartproduct (cupet_cart_no, cupet_prodno, cupet_cartprodcnt) " +
            "VALUES (#{cupet_cart_no}, #{cupet_prodno}, #{cupet_cartprodcnt})")
    @Options(keyProperty = "cupet_cartproduct_no")
    int insert(CartProdVO cartProd);
	
	@Delete("DELETE FROM cupetcartproduct WHERE cupet_cartproduct_no = #{cartprodnum}")
    int delete(int cartprodnum);
	
	@Delete("DELETE FROM cupetcartproduct WHERE cupet_cart_no = #{cartno}")
    int deleteCartAll(int cartno);
	
	@Select("select * from cupetcartproduct where cupet_cart_no = #{arg0} and cupet_prodno = #{arg1}")
	List<CartProdVO> findByCartnoAndProdno(int cart_no, int cupet_prodno);
	
	@Select("select * from cupetcartproduct where cupet_cart_no = #{cartnum}")
	List<CartProdVO> getCartProd (int cartnum);
	
	@Update("update cupetcartproduct set cupet_cartprodcnt = #{arg1} where cupet_cartproduct_no = #{arg0}")
	int updateQuantity(int cartprodnum, int newQuantity);
	
}