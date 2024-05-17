package com.onlinelearningsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "course_name" , nullable = false, length = 50)
    private String Name;

    @Column(name = "description" , nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "create_date" , nullable = false)
    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY) // Adding fetch type to optimize loading strategy
    @JoinColumn(name = "id_teacher" , nullable = false) // Explicitly specify join column
    private Teacher teacher;

//    @OneToOne(mappedBy = "course", fetch = FetchType.LAZY)
//    private Enrollment enrollment;

}
