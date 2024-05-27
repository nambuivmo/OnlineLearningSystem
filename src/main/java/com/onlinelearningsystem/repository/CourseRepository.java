package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(nativeQuery = true,
            value="Select c.id as id, c.course_name as name, c.create_date as createDate, c.description as description, CONCAT(t.first_name, ' ', t.last_name) as nameTeacher \n" +
                    "From course c \n" +
                    "JOIN teacher t ON c.id_teacher = t.id\n"+
                    "WHERE c.course_name LIKE CONCAT('%', ?1, '%') AND CONCAT(t.first_name, ' ', t.last_name) LIKE CONCAT('%', ?2, '%')"
    )
    Page<CourseDTO> findAllCourse(Pageable pageable,String courseName, String nameTeacher);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "INSERT INTO course (course_name, create_date, description, id_teacher)\n" +
                    "VALUES (?1, ?2, ?3, ?4)")

    void  createCourse(String courseName, LocalDate createDate, String description, long teacherId);

    @Query(nativeQuery = true,
            value="Select c.id as id, c.course_name as name, c.create_date as createDate, c.description as description, CONCAT(t.first_name, ' ', t.last_name) as nameTeacher \n" +
                    "From course c \n" +
                    "JOIN teacher t ON c.id_teacher = t.id\n"+
                    "WHERE c.course_name LIKE CONCAT('%', ?1, '%') and t.id=?2"
    )

    Page<CourseDTO> getCourseOfTeacher(Pageable pageable, String courseName,long idTeacher);
    @Query(nativeQuery = true,
            value="SELECT c.id AS id, " +
                    "c.course_name AS name, " +
                    "p.payment_date AS createDate, " +
                    "c.description AS description, " +
                    "CONCAT(t.first_name, ' ', t.last_name) AS nameTeacher " +
                    "FROM course c " +
                    "JOIN enrollment e ON c.id = e.id_course " +
                    "JOIN payment p ON e.id_payment = p.id " +
                    "JOIN student s ON e.id_student = s.id " +
                    "JOIN teacher t ON c.id_teacher = t.id " +
                    "WHERE s.id = ?2 " +
                    "AND c.course_name LIKE CONCAT('%', ?1, '%')")
    Page<CourseDTO> getCoursOfStudent(Pageable pageable, String courseName, long idStudent);
}
