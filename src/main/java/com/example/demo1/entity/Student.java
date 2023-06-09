package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String birthday;
    private String password;
    private boolean isEnabled;
    private LocalDateTime dateTime;
}
