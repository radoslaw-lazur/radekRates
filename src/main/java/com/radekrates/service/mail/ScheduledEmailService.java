package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduledEmailService {
    @Qualifier("sender2")
    private JavaMailSender javaMailSender;
    private EmailPreparation preparation;

    @Autowired
    public ScheduledEmailService(JavaMailSender javaMailSender, EmailPreparation preparation) {
        this.javaMailSender = javaMailSender;
        this.preparation = preparation;
    }

    public void send(final Mail mail, final String weatherData, String[] emails) {
        try {
            String scheduledMail = preparation.prepareScheduledEmail(mail, weatherData);
            mail.setMessage(scheduledMail);
            javaMailSender.send(preparation.createMimeMessageScheduler(mail, emails));
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }
}
