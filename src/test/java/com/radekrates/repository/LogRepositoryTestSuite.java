package com.radekrates.repository;

import com.radekrates.domain.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogRepositoryTestSuite {
    @Autowired
    private LogRepository logRepository;
    private Log log;

    @Before
    public void init() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 2, 4, 12, 11);
         log = new Log("test@tets.com", "log", localDateTime);
    }

    @Test
    public void testSaveLog() {
        //Given
        logRepository.save(log);
        Long logId = log.getId();
        //When
        Optional<Log> logDb = logRepository.findById(logId);
        //Then
        assertTrue(logDb.isPresent());
        assertEquals(log.getUserEmail(), logDb.get().getUserEmail());
        assertEquals(log.getLogInfo(), logDb.get().getLogInfo());
        assertEquals(log.getLocalDateTime(), logDb.get().getLocalDateTime());
    }

    @Test
    public void tesLogsSize() {
        //Given
        logRepository.save(log);
        //When
        Set<Log> logs = logRepository.findAll();
        //Then
        assertEquals(1, logs.size());
    }

    @After
    public void cleanUp() {
        logRepository.deleteAll();
    }
}
