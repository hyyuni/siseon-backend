package siseon.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SiseonBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiseonBackendApplication.class, args);
    }
}
