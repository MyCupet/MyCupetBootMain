package cupet.com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cupet.com.demo.mapper")
public class MyCupetBootMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCupetBootMainApplication.class, args);
	}

}
