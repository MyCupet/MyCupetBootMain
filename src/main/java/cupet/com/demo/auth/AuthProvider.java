package cupet.com.demo.auth;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Component
public class AuthProvider {

	public Map<String, Object> getAuthenticationByData(String token) {
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:9092").build();
		try {
			Mono<Map<String, Object>> res = webClient.get().uri("/BootMain/auth-token/user")
					.header("Authorization", token).retrieve()
					.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
					}).onErrorResume(WebClientResponseException.class, e -> {
						e.printStackTrace();
						return Mono.error(e);
					});

			System.out.println(res);
			return res.block();
		} catch (WebClientResponseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
