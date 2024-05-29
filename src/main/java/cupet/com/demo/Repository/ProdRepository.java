package cupet.com.demo.Repository;

import cupet.com.demo.entity.ShopVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdRepository extends JpaRepository<ShopVO, Integer>{

}
