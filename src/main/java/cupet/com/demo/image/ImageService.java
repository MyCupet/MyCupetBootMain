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

    public Object getImage(String image_type, String real_filename) {
        return amazonS3Client.getUrl(bucketName, image_type + "/" + real_filename);
    }

    public String uploadCloudAndSaveToDb(String image_type, MultipartFile file, String use_id) throws IOException {
        // 서버에 저장할 파일 이름을 생성 UUID
        String realFileName = UUID.randomUUID().toString();

        // 파일 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        // 저장될 위치 + 파일명
        String key = image_type + "/" + realFileName;

        // 클라우드에 파일 저장
        amazonS3Client.putObject(bucketName, key, file.getInputStream(), objectMetadata);
        amazonS3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);

        String fileUrl = amazonS3Client.getUrl(bucketName, key).toString();

        // ImageVO 생성 후 DB에 저장
        ImageVO imageVO = ImageVO.builder()
                .image_type(image_type)
                .use_id(use_id)
                .real_filename(realFileName)
                .content_type(file.getContentType())
                .size(String.valueOf(file.getSize()))
                .created_at(new Date())
                .build();

        // 이미지의 use_id와 동일한 데이터가 이미 존재하는지 확인
        List<ImageVO> existingImages = imageMapper.getImageById(use_id);
        if (!existingImages.isEmpty()) {
            // 이미지가 존재하면 해당 이미지를 삭제
            ImageVO existingImage = existingImages.get(0);
            imageMapper.deleteImage(existingImage.getUse_id());
            amazonS3Client.deleteObject(bucketName, existingImage.getImage_type() + "/" + existingImage.getReal_filename());
        }

        // 새 이미지 정보를 DB에 저장
        imageMapper.insertImage(imageVO);

        return fileUrl;
    }
    
    public String deleteImage(String image_type, String use_id) {
        List<ImageVO> existingImages = imageMapper.getImageById(use_id);
        	
        if (!existingImages.isEmpty()) {
            // 이미지가 존재하면 해당 이미지를 삭제
            ImageVO existingImage = existingImages.get(0);
            imageMapper.deleteImage(existingImage.getUse_id());
            amazonS3Client.deleteObject(bucketName, existingImage.getImage_type() + "/" + existingImage.getReal_filename());
        }
        
        return use_id;
    }

    public List<ImageVO> getImageDetails(String use_id) {
        return imageMapper.getImageById(use_id);
    }
}