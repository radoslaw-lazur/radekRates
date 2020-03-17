package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Qualifier("sender1")
    private JavaMailSender javaMailSender;
    private EmailPreparation preparation;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, EmailPreparation preparation) {
        this.javaMailSender = javaMailSender;
        this.preparation = preparation;
    }

    public void sendActivationLink(final Mail mail, final User user) {
        log.info("Starting e-mail preparation... to " + mail.getMailTo() + " : " + mail.getSubject());
        try {
            String activationMail = preparation.prepareActivationMail(mail, user);
            mail.setMessage(activationMail);
            javaMailSender.send(preparation.createMimeMessageActivation(mail));
            log.info("Email to " + mail.getMailTo() + " has been sent" + " regarding " + mail.getSubject());
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }

    public void sendTransaction(final Mail mail, final User user, final Transaction transaction) {
        log.info("Starting e-mail preparation... to " + mail.getMailTo() + " : " + mail.getSubject());
        try {
            String transactionMail = preparation.prepareTransactionMail(mail, user, transaction);
            mail.setMessage(transactionMail);
            javaMailSender.send(preparation.createMimeMessageTransaction(mail));
            log.info("Email to " + mail.getMailTo() + " has been sent" + " regarding " + mail.getSubject());
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }

    public void sendForgottenPassword(final Mail mail, final User user) {
        log.info("Starting e-mail preparation... to " + mail.getMailTo() + " : " + mail.getSubject());
        try {
            String forgottenPasswordMail = preparation.prepareForgottenPasswordEmail(user);
            mail.setMessage(forgottenPasswordMail);
            javaMailSender.send(preparation.createMimeMessageForgottenPassword(mail));
            log.info("Email to " + mail.getMailTo() + " has been sent" + " regarding " + mail.getSubject());
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }
}
