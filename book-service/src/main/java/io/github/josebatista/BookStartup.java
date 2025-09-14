package io.github.josebatista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookStartup {

    public static void main(String[] args) {
        SpringApplication.run(BookStartup.class, args);
    }

}
