package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import com.radekrates.domain.User;
import com.radekrates.service.UserServiceDb;
import com.radekrates.service.generators.CurrencyBaseGenerator;
import com.radekrates.service.generators.OpenWeatherGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@Slf4j
public class EmailScheduler {
    private ScheduledEmailService scheduledEmailService;
    private UserServiceDb userServiceDb;
    private CurrencyBaseGenerator currencyBaseGenerator;
    private OpenWeatherGenerator openWeatherGenerator;

    @Autowired
    public EmailScheduler(ScheduledEmailService scheduledEmailService, UserServiceDb userServiceDb,
                          CurrencyBaseGenerator currencyBaseGenerator, OpenWeatherGenerator openWeatherGenerator) {
        this.scheduledEmailService = scheduledEmailService;
        this.userServiceDb = userServiceDb;
        this.currencyBaseGenerator = currencyBaseGenerator;
        this.openWeatherGenerator = openWeatherGenerator;
    }

    @Scheduled (fixedDelay = 300000) // every 5 min
    //@Scheduled(cron = "0 0 10 * * *") //in real life
    public void sendScheduledEmail() {
        String currencyMessage = currencyBaseGenerator.generateCurrencyBaseString();
        String weatherMessage = openWeatherGenerator.generateOpenWeatherData();
        if (!userServiceDb.getAllUsers().isEmpty()) {
            scheduledEmailService.send(new Mail("radoslaw.lazur.dev@gmail.com",
                     LocalDate.now(ZoneId.of("Europe/Warsaw")) + " - " + "Daily data provided by " +
                    "Radoslaw's rates exchanges", currencyMessage), weatherMessage, makeUserEmailArray());
            log.info("Scheduled emails have been sent");
        }
    }

    private String[] makeUserEmailArray() {
        return userServiceDb.getAllUsers().stream()
                .map(User::getEmail)
                .toArray(String[]::new);
    }
}
