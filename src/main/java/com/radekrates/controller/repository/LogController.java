package com.radekrates.controller.repository;

import com.radekrates.domain.dto.log.LogDto;
import com.radekrates.mapper.LogMapper;
import com.radekrates.service.LogServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class LogController {
    private LogServiceDb logServiceDb;
    private LogMapper logMapper;

    @Autowired
    public LogController(LogServiceDb logServiceDb, LogMapper logMapper) {
        this.logServiceDb = logServiceDb;
        this.logMapper = logMapper;
    }

    @GetMapping(value = "/logs")
    public Set<LogDto> getLogs() {
        return logMapper.mapToLogDtoSet(logServiceDb.getAllLogs());
    }
}
