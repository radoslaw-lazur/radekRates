package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Qualifier("sender1")
    private JavaMailSender javaMailSender;
    private MailCreatorService mailCreatorService;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, MailCreatorService mailCreatorService) {
        this.javaMailSender = javaMailSender;
        this.mailCreatorService = mailCreatorService;
    }

    public void sendActivationLink(final Mail mail, final User user) {
        log.info("Starting e-mail preparation... to " + mail.getMailTo() + " : " + mail.getSubject());
        try {
            String activationMail = prepareActivationMail(mail, user);
            mail.setMessage(activationMail);
            javaMailSender.send(createMimeMessageActivation(mail));
            log.info("Email to " + mail.getMailTo() + " has been sent" + " regarding " + mail.getSubject());
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }

    public void sendTransaction(final Mail mail, final User user, final Transaction transaction) {
        log.info("Starting e-mail preparation... to " + mail.getMailTo() + " : " + mail.getSubject());
        try {
            String transactionMail = prepareTransactionMail(mail, user, transaction);
            mail.setMessage(transactionMail);
            javaMailSender.send(createMimeMessageTransaction(mail));
            log.info("Email to " + mail.getMailTo() + " has been sent" + " regarding " + mail.getSubject());
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessageActivation(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    private MimeMessagePreparator createMimeMessageTransaction(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    private String prepareActivationMail(final Mail mail, final User user) {
        return mailCreatorService.buildSignInNotificationEmail(mail.getMessage(), user);
    }

    private String prepareTransactionMail(final Mail mail, final User user, final Transaction transaction) {
        return mailCreatorService.buildTransactionEmail(mail.getMessage(), user, transaction);
    }
}
