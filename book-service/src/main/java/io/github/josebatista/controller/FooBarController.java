package io.github.josebatista.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/book-service")
public class FooBarController {

    private final Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
//    @Retry(name = "default") // 3 tentativas
    @Retry(name = "foo-bar") // configurado no application.yaml
    public String fooBar() {
        logger.info("Request to foo-bar is received!");
        ResponseEntity<String> response = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar", String.class);
        return response.getBody();
    }
}
