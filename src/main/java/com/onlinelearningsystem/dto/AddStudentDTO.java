package com.onlinelearningsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentDTO {
    private Long idAccount;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private boolean gender;
    private String address;
    private String phoneNumber;
}
