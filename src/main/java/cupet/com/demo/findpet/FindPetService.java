package cupet.com.demo.findpet;

import java.util.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cupet.com.demo.dto.CupetUserAddressVO;
import cupet.com.demo.dto.MissingPetVO;
import cupet.com.demo.image.ImageMapper;
import cupet.com.demo.image.ImageService;
import cupet.com.demo.image.ImageVO;
import cupet.com.demo.user.UserVO;
import cupet.com.demo.user.pet.PetMapper;
import cupet.com.demo.user.pet.PetVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindPetService {

	private final PetMapper petMapper;
	private final FindPetMapper findPetMapper;
	private final ImageMapper imageMapper;
	private final ImageService imageService;
	private final AmazonS3 amazonS3Client;

	public List<String> MissingPetList(String userid) {
		List<String> list = new ArrayList<>();
		return list;
	}

	public CupetUserAddressVO userGetLocate(String id) {
		CupetUserAddressVO cua = findPetMapper.getUserLocate(id);
		System.out.println(cua);

		return cua;
	}

	public int insertMissingPet(String id, MissingPetVO obj) {
		MissingPetVO ms = obj;
		ms.setCupet_user_id(id);
		ms.setCupet_pet_no(obj.getPetName());

		List<MissingPetVO> list = findPetMapper.getAllMissingPet(ms);
		System.out.println(list);
		for (MissingPetVO item : list) {
			if (item.getCupet_pet_no().equals(ms.getCupet_pet_no())) {
				return -1;
			}
		}
		int res = findPetMapper.insertMissingPet(ms);
		if (res > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public int findPetInsertPointDec(Map<String, Object> usr, int a) {
		int num = 0;

		String id = (String) usr.get("cupet_user_id");
		System.out.println("id : " + id);
		UserVO u = UserVO.builder().cupet_user_id(id).cupet_user_point(String.valueOf(a)).build();
		System.out.println("user: " + u);
		num = findPetMapper.findPetInsertPointDec(u);
		return num;
	}

	public List<MissingPetVO> getMarkerList(String lat, String lng) {

		List<MissingPetVO> list = findPetMapper.getMarkerList();
		System.out.println("missing pet list : " + list);
		System.out.println(lat);
		System.out.println(lng);
		Comparator<MissingPetVO> cp = new Comparator<MissingPetVO>() {
			@Override
			public int compare(MissingPetVO o1, MissingPetVO o2) {
				return Double.compare(o1.nodeDistance(lat, lng), o2.nodeDistance(lat, lng));
			}
		};

		Collections.sort(list, cp);

		if (list.size() > 10) {
			list.subList(0, 10);
		}

		return list;
	}
	
	@Value("${spring.s3.bucket}")
    private String bucketName;

	public Map<String, Object> getPetDetailInfo(String petno) {
		Map<String, Object> res = findPetMapper.getPetDetailInfo(petno);
		ImageVO img = imageMapper.getImageByIDtoDetail(petno);
		if(img == null) {
			res.put("ImageUrl", "img/logo.png");
		} else {
			Object temp = amazonS3Client.getUrl(bucketName, img.getImage_type() + "/" + img.getReal_filename());
			res.put("ImageUrl", temp);
		}
		System.out.println(res);
		return res;
	}

	public String addComment(String id, String nickname, String content, String petNo) {
		// TODO Auto-generated method stub
		MissingPetCommentVO mpc = MissingPetCommentVO.builder().comment(content).cupet_pet_no(petNo).cupet_user_id(id)
				.cupet_user_nickname(nickname).build();
	
		int num = findPetMapper.addComment(mpc);
		
		if( num != 0 ) {
			MissingPetCommentVO temp =  findPetMapper.getMisssingPetCommentDetail(content);
			String comment_num = temp.getComment_no();
			return comment_num;
		}
		
		return "failed";
		
		
	}

	public List<MissingPetCommentVO> getMisssingPetComments(String petNo) {
		List<MissingPetCommentVO> list = findPetMapper.getMisssingPetComments(petNo);
		System.out.println(list);
		return list;
	}
}
