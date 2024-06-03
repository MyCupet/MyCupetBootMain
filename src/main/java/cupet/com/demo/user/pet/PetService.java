package cupet.com.demo.user.pet;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetMapper petMapper;

    public List<PetVO> petView(String cupet_user_id) {
        try {
            return petMapper.getAllPets(cupet_user_id);
        } catch (Exception e) {
            System.err.println("Error fetching pet view: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public PetVO petInsert(PetVO petVO) {
        try {
            petMapper.insertPet(petVO);
            return petVO;
        } catch (Exception e) {
            System.err.println("Error inserting pet: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public PetVO petUpdate(PetVO petVO) {
        try {
            petMapper.updatePet(petVO);
            return petVO;
        } catch (Exception e) {
            System.err.println("Error inserting pet: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public int petDelete(int cupet_pet_no) {
        try {
            petMapper.deletePet(cupet_pet_no);
            return cupet_pet_no;
        } catch (Exception e) {
            System.err.println("Error deleting pet: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}