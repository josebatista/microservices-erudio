package io.github.josebatista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NamingServerStartup {

    public static void main(String[] args) {
        SpringApplication.run(NamingServerStartup.class, args);
    }

}
