package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.dto.AddTeacherDTO;
import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(nativeQuery = true,
            value="Select t.id as id, t.first_name as firstName, t.last_name as lastName, t.dob as dob," +
                    "t.gender as gender, t.address as address, t.phone_number as phoneNumber from teacher t\n" +
                    "WHERE t.first_name LIKE CONCAT('%', ?1, '%') AND t.last_name LIKE CONCAT('%', ?2, '%')"
    )
    Page<TeacherDTO> findAllTeacher(Pageable pageable,String firstName, String lastName);

    @Query(nativeQuery = true,
            value="Select t.id as id, t.first_name as firstName, t.last_name as lastName, t.dob as dob," +
                    "t.gender as gender, t.address as address, t.phone_number as phoneNumber from teacher t\n" +
                    "WHERE t.id LIKE CONCAT('%', ?1, '%')"
    )
    TeacherDTO getTeacher(long id);




//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true,
//            value = "INSERT INTO teacher (first_name,address,dob,last_name,gender,phone_number,id_account)\n" +
//                    "VALUES (?1, ?2, ?3,?4, ?5, ?6,?7)")
//    void addTeacher(String getFirstName, String getAddress, LocalDate getDob, String getLastName,
//                             boolean getGender, String getPhoneNumber, Long getIdAccount);
}
