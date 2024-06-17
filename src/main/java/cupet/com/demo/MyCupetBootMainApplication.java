package cupet.com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/app.properties")
@MapperScan("cupet.com.demo")
public class MyCupetBootMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCupetBootMainApplication.class, args);
	}

}