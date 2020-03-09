package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTestSuite {
    @InjectMocks
    private EmailService emailService;
    @Mock
    private JavaMailSender javaMailSender;
    private Mail mail;

    //@Before
    public void init() {
        mail = new Mail("test@test.com", "Subject", "message");
    }

    //@Test
    public void testSendingActivationEmail() {
        //Given
        String mockedActivationLink = mock(String.class);

        //When
        emailService.sendActivationLink(mail, new User());
        //Then
        Mockito.verify(javaMailSender).send(Mockito.isA(MimeMessagePreparator.class));
    }

    //@Test
    public void testSendingTransactionEmail() {
        //Given
        //When
        emailService.sendTransaction(mail, new User(), new Transaction());
        //Then
        Mockito.verify(javaMailSender).send(Mockito.isA(MimeMessagePreparator.class));
    }
}
