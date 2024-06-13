package cupet.com.demo.shop;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cupet.com.demo.dto.PageRequestVO;

@Mapper
@Repository("shopMapper")
public interface ShopMapper {
	@Select("select * from cupetshop")
	List<ShopVO> getList(PageRequestVO pageRequestVO);
	
	@Select("select * from cupetshop where cupet_prodno = #{cupet_prodno}")
	ShopVO findByProdNo2(int cupet_prodno);
	
	@Select("select count(*) from cupetshop")
	int getTotalCount(PageRequestVO pageRequestVO);
	
	@Select({
	    "<script> select * from cupetshop where cupet_prodno in <foreach item='item' index='index' collection='array' open='(' separator=',' close=')'>",
	    "#{item} </foreach> </script>"})
	List<ShopVO> findByNo(int[] prodnos);
	
	 @Update("update cupetshop set cupet_prodcnt = cupet_prodcnt - #{orderCount} where cupet_prodno = #{prodno}")
	 void updateProductCount(@Param("prodno") int prodno, @Param("orderCount") int orderCount);
	
	 @Insert("insert into cupetshop (cupet_prodname, cupet_prodprice, cupet_prodimgpath, cupet_proddiscountper, cupet_prodcont, cupet_prodcnt)"
	 		+ "values (#{cupet_prodname}, #{cupet_prodprice}, #{cupet_prodimgpath}, #{cupet_proddiscountper}, #{cupet_prodcont}, #{cupet_prodcnt})")
	@Options(keyProperty = "cupet_prodno")
	 int insert(ShopVO shopVO);
	 
}