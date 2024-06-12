package cupet.com.demo.pay;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IamportConfig {
    @Bean
    public IamportClient iamportClient() {
        return new IamportClient("iamport.api_key", "iamport.api_secret");
    }
}
