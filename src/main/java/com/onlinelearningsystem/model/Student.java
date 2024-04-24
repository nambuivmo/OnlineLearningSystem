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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private long idStudent;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstNameStudent;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastNameStudent;

    @Column(name = "dob", nullable = false)
    private LocalDate dobStudent;

    @Column(name = "gender_student", nullable = false, length = 10)
    private String genderStudent;

    @Column(name = "address_student", nullable = false)
    private String addressStudent;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumberStudent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;


}
