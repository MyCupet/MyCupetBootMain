package cupet.com.demo.image;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("imageMapper")
public interface ImageMapper {
    @Insert("INSERT INTO cupetimage(image_type, use_id, real_filename, content_type, size, created_at) VALUES(#{image_type}, #{use_id}, #{real_filename}, #{content_type}, #{size}, #{created_at})")
    void insertImage(ImageVO imageVO);

    @Select("SELECT * FROM cupetimage WHERE use_id = #{use_id}")
    List<ImageVO> getImageById(@Param("use_id") String use_id);
}