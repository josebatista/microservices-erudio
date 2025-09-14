package io.github.josebatista.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-service")
public class FooBarController {

    private final Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
//    @Retry(name = "default") // 3 tentativas
//    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod") // configurado no application.yaml
//    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String fooBar() {
//        logger.info("Request to foo-bar is received!");
//        ResponseEntity<String> response = new RestTemplate()
//                .getForEntity("http://localhost:8080/foo-bar", String.class);
//        return response.getBody();
        return "Foo bar!!";
    }

    private String fallbackMethod(Exception ex) {
        return "fallbackMethod foo-bar!!";
    }
}
