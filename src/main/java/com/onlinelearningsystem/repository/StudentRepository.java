package com.onlinelearningsystem.repository;


import com.onlinelearningsystem.dto.StudentDTO;
import com.onlinelearningsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(nativeQuery = true,
            value="Select s.id as id, s.first_name as firstName, s.last_name as lastName, s.dob as dob," +
                    "s.gender as gender, s.address as address, s.phone_number as phoneNumber from student s"
    )
    List<StudentDTO> findAllStudent();

    @Query(nativeQuery = true,
            value = "SELECT s.id AS id, s.first_name AS firstName, s.last_name AS lastName, s.dob AS dob, " +
                    "s.gender AS gender, s.address AS address, s.phone_number AS phoneNumber " +
                    "FROM student s " +
                    "WHERE s.first_name LIKE CONCAT('%', ?1, '%') AND s.last_name LIKE CONCAT('%', ?2, '%')")
    List<StudentDTO> getUser(String firstName, String lastName);

//    @Query(nativeQuery = true,
//            value = "UPDATE student s SET s.first_name = ?2, s.last_name = ?3 WHERE s.id_student = ?1")
//    StudentInforListDTO updateStudent(Long id);
}
