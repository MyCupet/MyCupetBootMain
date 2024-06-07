package cupet.com.demo.image;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api1/images")
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final ImageService imageService;

    @GetMapping("{image_type}/{use_id}")
    public CustomResponse<Object> getImage(
            @PathVariable("image_type") String image_type, 
            @PathVariable("use_id") String use_id) {
        log.info("Get Image at S3");
        List<ImageVO> imageDetails = imageService.getImageDetails(use_id);
        if (imageDetails.isEmpty()) {
            return CustomResponse.error("image not found");
        }
        Object imageStream = imageService.getImage(image_type, imageDetails.get(0).getImage_id());

        return CustomResponse.ok("Get Image URL", imageStream);
    }

    @PostMapping("/upload/{image_type}")
    public CustomResponse<String> uploadImage(
            @PathVariable("image_type") String imageType, 
            @RequestParam("file") MultipartFile file, 
            @RequestParam("use_id") String use_id) throws IOException {
        log.info("Upload an image file");
        String fileUrl = imageService.uploadCloudAndSaveToDb(imageType, file, use_id);
        return CustomResponse.ok("Upload a file", fileUrl);
    }
}
