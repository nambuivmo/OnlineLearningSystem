package com.onlinelearningsystem.repository;


import com.onlinelearningsystem.dto.StudentInforListDTO;
import com.onlinelearningsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(nativeQuery = true,
            value="Select s.id_student as id, s.first_name as firstName, s.last_name as lastName, s.dob as dob," +
                    "s.gender_student as gender, s.address_student as address, s.phone_number as phoneNumber from student s"
    )
    List<StudentInforListDTO> findAllStudent();
}
