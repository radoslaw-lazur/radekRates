package com.radekrates.mapper;

import com.radekrates.domain.Log;
import com.radekrates.domain.dto.log.LogDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Before
    public void init() {
        log = new Log(1L, "Log");
        logDto = new LogDto(1L, "logDto");
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
    }
}
