package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import com.radekrates.domain.User;
import com.radekrates.service.UserServiceDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduledEmailService {
    @Qualifier("sender2")
    private JavaMailSender javaMailSender;
    private MailCreatorService mailCreatorService;
    private UserServiceDb userServiceDb;

    @Autowired
    public ScheduledEmailService(JavaMailSender javaMailSender, MailCreatorService mailCreatorService,
                                 UserServiceDb userServiceDb) {
        this.javaMailSender = javaMailSender;
        this.mailCreatorService = mailCreatorService;
        this.userServiceDb = userServiceDb;
    }

    public void send(final Mail mail, final String weatherData) {
        try {
            String scheduledMail = prepareScheduledEmail(mail, weatherData);
            mail.setMessage(scheduledMail);
            javaMailSender.send(createMimeMessage(mail));
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(makeUserEmailArray());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    private String[] makeUserEmailArray() {
        return userServiceDb.getAllUsers().stream()
                .map(User::getEmail)
                .toArray(String[]::new);
    }

    private String prepareScheduledEmail(final Mail mail, final String weatherData) {
        return mailCreatorService.buildScheduledEmail(mail.getMessage(), weatherData);
    }
}
