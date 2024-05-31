package cupet.com.demo.user.pet;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetMapper petMapper;

    public PetVO petView(String cupet_user_id, int cupet_pet_no) {
        try {
            return petMapper.getPetview(cupet_user_id);
        } catch (Exception e) {
            System.err.println("Error fetching board view: " + e.getMessage());
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
}