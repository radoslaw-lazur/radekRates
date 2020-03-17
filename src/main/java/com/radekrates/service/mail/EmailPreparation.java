package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailPreparation {
    private MailCreatorService mailCreatorService;


    @Autowired
    public EmailPreparation(MailCreatorService mailCreatorService) {
        this.mailCreatorService = mailCreatorService;

    }

    public MimeMessagePreparator createMimeMessageActivation(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    public MimeMessagePreparator createMimeMessageTransaction(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    public MimeMessagePreparator createMimeMessageScheduler(final Mail mail, final String[] emails) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(emails);
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    public MimeMessagePreparator createMimeMessageForgottenPassword(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    public String prepareActivationMail(final Mail mail, final User user) {
        return mailCreatorService.buildSignInNotificationEmail(mail.getMessage(), user);
    }

    public String prepareTransactionMail(final Mail mail, final User user, final Transaction transaction) {
        return mailCreatorService.buildTransactionEmail(mail.getMessage(), user, transaction);
    }

    public String prepareScheduledEmail(final Mail mail, final String weatherData) {
        return mailCreatorService.buildScheduledEmail(mail.getMessage(), weatherData);
    }

    public String prepareForgottenPasswordEmail(final User user) {
        return mailCreatorService.buildForgottenPasswordEmail(user);
    }
}
