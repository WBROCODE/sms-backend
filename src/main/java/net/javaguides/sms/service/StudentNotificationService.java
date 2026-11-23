package net.javaguides.sms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentNotificationService {
    // Async
    @Async
    public void sendWelcomeEmail(String email) {
        log.info("Sending welcome email to {} ...", email);
        try {
            Thread.sleep(3000);          // pretend sending an email takes 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("Welcome email sent to {}", email);
    }
}