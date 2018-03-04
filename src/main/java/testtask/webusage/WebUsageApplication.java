package testtask.webusage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import testtask.webusage.service.WebEventRepository;

@SpringBootApplication
public class WebUsageApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebUsageApplication.class, args);
	}
}
