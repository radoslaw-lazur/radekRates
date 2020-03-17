package com.radekrates.domain.dto.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class LogDto {
    private Long id;
    private String userEmail;
    private String logInfo;
    private LocalDateTime localDateTime;
}
