package cupet.com.demo.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageVO {
    private String image_id;
    private String image_type;
    private String use_id;
    private String original_name; // 파일이름
    private String real_filename; // 파일명
    private String content_type;
    private String size;
    private Date created_at;
}