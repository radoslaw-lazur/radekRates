package com.radekrates.mapper;

import com.radekrates.domain.Log;
import com.radekrates.domain.dto.log.LogDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogMapperTestSuite {
    @Autowired
    private LogMapper logMapper;
    private Log log;
    private LogDto logDto;
    private LocalDateTime localDateTime;

    @Before
    public void init() {
        localDateTime = LocalDateTime.of(2020, 2, 4, 12, 11);
        log = new Log(1L, "Log", localDateTime);
        logDto = new LogDto(1L, "logDto", localDateTime);
    }

    @Test
    public void shouldMapToLogDto() {
        //Given
        //When
        LogDto mappedLogDto = logMapper.mapToLogDto(log);
        //Then
        assertEquals(log.getId(), mappedLogDto.getId());
        assertEquals(log.getLogInfo(), mappedLogDto.getLogInfo());
    }

    @Test
    public void shouldMapToLog() {
        //Given
        //When
        Log mappedLog = logMapper.mapToLog(logDto);
        //Then
        assertEquals(logDto.getId(), mappedLog.getId());
        assertEquals(logDto.getLogInfo(), mappedLog.getLogInfo());
        assertEquals(logDto.getLocalDateTime(), mappedLog.getLocalDateTime());
    }

    @Test
    public void shouldMapToLogSetDto() {
        //Given
        Set<Log> logs = new HashSet<>();
        logs.add(log);
        //When
        Set<LogDto> logDtos = logMapper.mapToLogDtoSet(logs);
        //Then
        assertEquals(logs.size(), logDtos.size());
        assertEquals(logs.iterator().next().getId(), logDtos.iterator().next().getId());
        assertEquals(logs.iterator().next().getLogInfo(), logDtos.iterator().next().getLogInfo());
        assertEquals(logs.iterator().next().getLocalDateTime(), logDtos.iterator().next().getLocalDateTime());
    }
}
