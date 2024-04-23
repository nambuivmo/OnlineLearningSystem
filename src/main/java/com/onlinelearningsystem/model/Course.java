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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course ")
    private long courseID;

    @Column(name = "course_name" , nullable = false, length = 50)
    private String courseName;

    @Column(name = "course_description " , nullable = false, columnDefinition = "TEXT")
    private String courseDescription;

    @Column(name = "create_date" , nullable = false)
    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY) // Adding fetch type to optimize loading strategy
    @JoinColumn(name = "teacher_id" , nullable = false) // Explicitly specify join column
    private Teacher teacher;

//    @OneToOne(mappedBy = "course", fetch = FetchType.LAZY)
//    private Enrollment enrollment;

}
