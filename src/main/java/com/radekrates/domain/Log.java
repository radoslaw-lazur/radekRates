package com.radekrates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "LOGS")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "LOG_ID")
    private Long id;
    @NotNull
    @Column(name = "LOG_USER_EMAIL")
    private String userEmail;
    @NotNull
    @Column(name = "LOG_INFO")
    private String logInfo;
    @NotNull
    @Column(name = "LOG_DATE")
    private LocalDateTime localDateTime;

    public Log(String userEmail, String logInfo, LocalDateTime localDateTime) {
        this.userEmail = userEmail;
        this.logInfo = logInfo;
        this.localDateTime = localDateTime;
    }
}
