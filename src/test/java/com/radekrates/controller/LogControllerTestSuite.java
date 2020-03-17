package com.radekrates.controller;

import com.radekrates.controller.repository.LogController;
import com.radekrates.domain.Log;
import com.radekrates.domain.dto.log.LogDto;
import com.radekrates.mapper.LogMapper;
import com.radekrates.service.LogServiceDb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LogController.class)
public class LogControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LogServiceDb logServiceDb;
    @MockBean
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
    public void shouldGetEmptyLogSet() throws Exception {
        //Given
        Set<LogDto> logDtos = new HashSet<>();
        when(logMapper.mapToLogDtoSet(logServiceDb.getAllLogs())).thenReturn(logDtos);
        //When & Then
        mockMvc.perform(get("/v1/logs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetLogSet() throws Exception {
        //Given
        Set<LogDto> logDtos = new HashSet<>();
        logDtos.add(logDto);
        when(logMapper.mapToLogDtoSet(logServiceDb.getAllLogs())).thenReturn(logDtos);
        //When & Then
        mockMvc.perform(get("/v1/logs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].logInfo", is("logDto")))
                .andExpect(jsonPath("$[0].localDateTime", is("2020-02-04T12:11:00")));
    }
}
