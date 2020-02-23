package com.radekrates.mapper;

import com.radekrates.domain.Log;
import com.radekrates.domain.dto.log.LogDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LogMapper {
    public Log mapToLog(final LogDto logDto) {
        return new Log(logDto.getId(), logDto.getLogInfo());
    }
    public LogDto mapToLogDto(final Log log) {
        return new LogDto(log.getId(), log.getLogInfo());
    }
    public Set<LogDto> mapToLogDtoSet(final Set<Log> logs) {
        return logs.stream()
                .map(l -> new LogDto(l.getId(), l.getLogInfo()))
                .collect(Collectors.toSet());
    }
}
