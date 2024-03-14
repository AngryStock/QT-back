package qt.qr_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //날짜 DB 자동생성용
@SpringBootApplication
public class QrBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrBackendApplication.class, args);
	}

}
