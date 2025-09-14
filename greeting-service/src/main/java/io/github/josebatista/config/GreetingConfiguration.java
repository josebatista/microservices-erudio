package io.github.josebatista.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties("greeting-service")
@RefreshScope // Usado para atualizar as configuracoes apos alteracao do config server, chamar o actuator/refresh (POST)
public class GreetingConfiguration {
    private String greeting;
    private String defaultValue;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
