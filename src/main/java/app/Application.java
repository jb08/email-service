package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import app.config.PostgresConfig;
import app.config.SwaggerConfig;

@SpringBootApplication
@Import({SwaggerConfig.class, PostgresConfig.class})
@ComponentScan({"controller", "service", "persistence"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}