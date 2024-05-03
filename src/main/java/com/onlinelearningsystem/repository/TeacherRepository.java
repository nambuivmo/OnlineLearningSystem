package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(nativeQuery = true,
            value="Select t.id_teacher as id, t.first_name as firstName, t.last_name as lastName, t.dob as dob," +
                    "t.gender as gender, t.address as address, t.phone_number as phoneNumber from teacher t"
    )
    List<TeacherDTO> findAllTeacher();

    @Query(nativeQuery = true,
            value="Select t.id_teacher as id, t.first_name as firstName, t.last_name as lastName, t.dob as dob," +
                    "t.gender as gender, t.address as address, t.phone_number as phoneNumber from teacher t" +
                    "WHERE t.first_name LIKE CONCAT('%', ?1, '%') AND t.last_name LIKE CONCAT('%', ?2, '%')"
    )
    List<TeacherDTO> getTeacher(String firstName, String lastName);
}
