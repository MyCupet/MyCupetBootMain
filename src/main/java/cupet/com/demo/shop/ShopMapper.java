package cupet.com.demo.shop;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cupet.com.demo.dto.PageRequestVO;

@Mapper
@Repository("shopMapper")
public interface ShopMapper {
	@Select("select * from cupetshop order by cupet_prodno desc limit #{size} offset #{skip}")
    List<ShopVO> getList(@Param("size") int size, @Param("skip") int skip);

    @Select("select count(*) from cupetshop")
    int getTotalCount();
	
	@Select("select * from cupetshop where cupet_prodno = #{cupet_prodno}")
	ShopVO findByProdNo2(int cupet_prodno);
	
	@Select({
	    "<script> select * from cupetshop where cupet_prodno in <foreach item='item' index='index' collection='array' open='(' separator=',' close=')'>",
	    "#{item} </foreach> </script>"})
	List<ShopVO> findByNo(int[] prodnos);
	
	 @Update("update cupetshop set cupet_prodcnt = cupet_prodcnt - #{orderCount} where cupet_prodno = #{prodno}")
	 void updateProductCount(@Param("prodno") int prodno, @Param("orderCount") int orderCount);
	
	 @Insert("insert into cupetshop (cupet_prodname, cupet_prodprice, cupet_proddiscountper, cupet_prodcont, cupet_prodcnt) " +
		        "values (#{cupet_prodname}, #{cupet_prodprice}, #{cupet_proddiscountper}, #{cupet_prodcont}, #{cupet_prodcnt})")
	@Options(useGeneratedKeys = true, keyProperty = "cupet_prodno", keyColumn = "cupet_prodno")
	int insert(ShopVO shopVO);
	 
	 @Delete("delete from cupetshop where cupet_prodno = #{cupet_prodno}")
	    int delete(@Param("cupet_prodno") int cupet_prodno);

}