package net.javaguides.sms.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Clock smsClock() {
        System.out.println(">>> @Bean method smsClock() called");
        return Clock.systemDefaultZone();
    }
}