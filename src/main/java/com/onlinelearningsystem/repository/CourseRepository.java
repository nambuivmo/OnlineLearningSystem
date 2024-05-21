package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(nativeQuery = true,
            value="Select c.id as id, c.course_name as name, c.create_date as createDate, c.description as description, t.first_name as firstName, t.last_name as lastName \n" +
                    "From course c \n" +
                    "JOIN teacher t ON c.id_teacher = t.id\n"+
                    "WHERE c.course_name LIKE CONCAT('%', ?1, '%') AND t.first_name LIKE CONCAT('%', ?2, '%') AND t.last_name LIKE CONCAT('%', ?3, '%')"
    )
    Page<CourseDTO> findAllCourse(Pageable pageable,String courseName, String firstName, String lastName);

    @Query(nativeQuery = true,
            value = "Select c.id as id, c.course_name as name, c.create_date as createDate, c.description as description, t.first_name as firstName, t.last_name as lastName \n" +
                    "From course c \n" +
                    "JOIN teacher t ON c.id_teacher = t.id\n"+
                    "WHERE c.course_name LIKE CONCAT('%', ?1, '%') AND t.first_name LIKE CONCAT('%', ?2, '%') AND t.last_name LIKE CONCAT('%', ?3, '%')")
    List<CourseDTO> getCourse(String courseName, String firstName, String lastName);

    @Query(nativeQuery = true,
            value = "INSERT INTO course (course_name, create_date, description, id_teacher)\n" +
                    "VALUES (?1, ?2, ?3, ?4)")
    @Modifying
    void  createCourse(String courseName, LocalDate createDate, String description, long teacherId);

}
