package com.onlinelearningsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher")
    private long idTeacher;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstNameTeacher;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastNameTeacher;

    @Column(name = "dob", nullable = false)
    private LocalDate dobTeacher;

    @Column(nullable = false, length = 10)
    private String genderTeacher;

    @Column(nullable = false)
    private String addressTeacher;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumberTeacher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;
}
