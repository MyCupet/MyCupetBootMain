package cupet.com.demo.findpet;


import java.util.*;

import org.springframework.stereotype.Service;

import cupet.com.demo.dto.CupetUserAddressVO;
import cupet.com.demo.dto.MissingPetVO;
import cupet.com.demo.dto.UserVO;
import cupet.com.demo.user.pet.PetMapper;
import cupet.com.demo.user.pet.PetVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindPetService {

	private final PetMapper petMapper;
	private final FindPetMapper findPetMapper;
	
	public List<String> MissingPetList(String userid){
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
		for(MissingPetVO item : list) {
			if(item.getCupet_pet_no().equals(ms.getCupet_pet_no())) {
				return -1;
			}
		}
		int res = findPetMapper.insertMissingPet(ms);
		if(res > 0) {
			return 1;
		}else {
			return 0;
		}
	}

	public int findPetInsertPointDec(Map<String, Object> usr, int a) {
		int num=0;
		
		String id  = (String) usr.get("cupet_user_id");
		System.out.println("id : " +id);
		UserVO u = UserVO.builder().cupet_user_id(id).cupet_user_point(String.valueOf(a)).build();
		System.out.println("user: "+u);
		num =findPetMapper.findPetInsertPointDec(u);
		return num;
	}
}
