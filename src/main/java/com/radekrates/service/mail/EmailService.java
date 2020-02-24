package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    private MailCreatorService mailCreatorService;
    private static final String SUBJECT_TRANSACTION = "New Transaction from Radoslaw's Rates Exchanges";

    @Autowired
    public EmailService(JavaMailSender javaMailSender, MailCreatorService mailCreatorService) {
        this.javaMailSender = javaMailSender;
        this.mailCreatorService = mailCreatorService;
    }

    public void send(final Mail mail, final User user, final Transaction transaction) {
        log.info("Starting e-mail preparation... to " + mail.getMailTo() + " : " + mail.getSubject());
        try {
            javaMailSender.send(createMimeMessage(mail, user, transaction));
            log.info("Email to " + mail.getMailTo() + " has been sent" + " regarding " + mail.getSubject());
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }

    public MimeMessagePreparator createMimeMessage(final Mail mail, final User user, final Transaction transaction) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            if (mail.getSubject().equals(SUBJECT_TRANSACTION)) {
                messageHelper.setText(mailCreatorService.buildTransactionEmail(mail.getMessage(), user, transaction), true);
            } else {
                messageHelper.setText(mailCreatorService.buildSignInNotificationEmail(mail.getMessage(), user), true);
            }
        };
    }
}
