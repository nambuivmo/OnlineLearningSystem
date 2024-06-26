package com.onlinelearningsystem.repository;


import com.onlinelearningsystem.dto.SearchStudentDTO;
import com.onlinelearningsystem.dto.StudentDTO;
import com.onlinelearningsystem.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(nativeQuery = true,
            value="Select s.id as id, s.first_name as firstName, s.last_name as lastName, s.dob as dob," +
                    "s.gender as gender, s.address as address, s.phone_number as phoneNumber " +
                    "from student s\n"+
                    "WHERE CONCAT(s.first_name, ' ', s.last_name) LIKE CONCAT('%', ?1, '%')"
    )
    Page<SearchStudentDTO> findAllStudent(Pageable pageable, String nameStudent);

    @Query(nativeQuery = true,
            value="Select s.id as id, s.first_name as firstName, s.last_name as lastName, s.dob as dob," +
                    "s.gender as gender, s.address as address, s.phone_number as phoneNumber " +
                    "from student s\n"+
                    "WHERE s.id LIKE CONCAT('%', ?1, '%')"
    )
    StudentDTO getStudent(long id);


//    @Query(nativeQuery = true,
//            value = "UPDATE student s SET s.first_name = ?2, s.last_name = ?3 WHERE s.id_student = ?1")
//    StudentInforListDTO updateStudent(Long id);
}
