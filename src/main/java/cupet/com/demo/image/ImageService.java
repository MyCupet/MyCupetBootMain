package cupet.com.demo.image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3 amazonS3Client; // S3
    private final ImageMapper imageMapper;

    @Value("${spring.s3.bucket}")
    private String bucketName;

    public Object getImage(String image_type, String image_id) {
        return amazonS3Client.getUrl(bucketName, image_type + "/" + Long.valueOf(image_id));
    }

    public String uploadCloudAndSaveToDb(String image_type, MultipartFile file, String use_id) throws IOException {
        // Generate UUID for the file name
        String realFileName = UUID.randomUUID().toString();

        // File metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        // S3 key
        String key = image_type + "/" + realFileName;

        // Upload to S3
        amazonS3Client.putObject(bucketName, key, file.getInputStream(), objectMetadata);
        amazonS3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);

        String fileUrl = amazonS3Client.getUrl(bucketName, key).toString();

        // Create ImageVO and insert into DB
        ImageVO imageVO = ImageVO.builder()
                .image_type(image_type)
                .use_id(use_id)
                .real_filename(realFileName)
                .original_name(file.getOriginalFilename())
                .content_type(file.getContentType())
                .size(String.valueOf(file.getSize()))
                .created_at(new Date())
                .build();

        imageMapper.insertImage(imageVO);

        return fileUrl;
    }

    public List<ImageVO> getImageDetails(String use_id) {
        return imageMapper.getImageById(use_id);
    }
}