package web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import web.app.config.WebConfig;

@SpringBootApplication
@Import(value={WebConfig.class})
@ComponentScan(basePackages={"web.app","web.app.ui"})
public class WebApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
