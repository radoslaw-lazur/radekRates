package com.radekrates.service.mail;

import com.radekrates.domain.Mail;
import com.radekrates.service.UserServiceDb;
import com.radekrates.service.generators.CurrencyBaseGenerator;
import com.radekrates.service.generators.OpenWeatherGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    @Scheduled (fixedDelay = 10000)
    public void sendScheduledEmail() {
        String currencyMessage = currencyBaseGenerator.generateCurrencyBaseString();
        String weatherMessage = openWeatherGenerator.generateOpenWeatherData();
        if (!userServiceDb.getAllUsers().isEmpty()) {
            scheduledEmailService.send(new Mail("radoslaw.lazur.dev@gmail.com", "Daily data provided by " +
                    "Radoslaw's rates exchanges", currencyMessage), weatherMessage);
            log.info("Scheduled emails have been sent");
        }
    }
}
