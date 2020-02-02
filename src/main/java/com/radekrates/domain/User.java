package com.radekrates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "USER_ID")
    private Long id;
    @NotNull
    @Column(name = "USER_EMAIL")
    private String email;
    @NotNull
    @Column(name = "USER_PASSWORD")
    private String password;
    @NotNull
    @Column(name = "USER_FIRSTNAME")
    private String userFirstName;
    @NotNull
    @Column(name = "USER_LASTNAME")
    private String userLastName;
    @NotNull
    @Column(name = "USER_AGE")
    private int age;
    @NotNull
    @Column(name = "USER_COUNTRY")
    private String country;
    @NotNull
    @Column(name = "USER_ISACTIVE")
    private boolean isActive;
    @NotNull
    @Column(name = "USER_BLOCKED")
    private boolean isBlocked;
    @OneToMany(
            targetEntity = Iban.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Iban> ibans = new HashSet<>();
    @OneToMany(
            targetEntity = Transaction.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Transaction> transactions = new HashSet<>();

    public User(Long id, String email, String password, String userFirstName, String userLastName, int age,
                String country, boolean isActive, boolean isBlocked) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.age = age;
        this.country = country;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
    }
    public User(String email, String password, String userFirstName, String userLastName, int age, String country,
                boolean isActive, boolean isBlocked) {
        this.email = email;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.age = age;
        this.country = country;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
    }
}
