package com.radekrates.service;

import com.radekrates.domain.Log;
import com.radekrates.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LogServiceDb {
    private LogRepository logRepository;

    @Autowired
    public LogServiceDb(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log saveLog(final Log log) {
        return logRepository.save(log);
    }

    public Set<Log> getAllLogs() {
        return logRepository.findAll();
    }
}
