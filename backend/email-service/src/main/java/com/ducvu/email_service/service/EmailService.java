package com.ducvu.email_service.service;

import com.ducvu.email_service.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void sendEmail(String message) {
        log.info("Email service; Received message from queue: {}", message);
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromMail);
            simpleMailMessage.setTo(message);
            simpleMailMessage.setSubject("Ecommerce");
            simpleMailMessage.setText("You have placed order successfully");
            javaMailSender.send(simpleMailMessage);

            log.info("Email sent successfully to {}", message);
        } catch (Exception e) {
            log.error("Error sending email: {}", e.getMessage(), e);
            throw e;
        }
    }
}
