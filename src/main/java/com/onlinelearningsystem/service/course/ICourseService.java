package com.onlinelearningsystem.service.course;

import com.onlinelearningsystem.dto.AddUpdateCourseDTO;
import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.dto.EnrollCouseDTO;
import com.onlinelearningsystem.model.Course;
import com.onlinelearningsystem.response.PageResponse;

public interface ICourseService {
    PageResponse<CourseDTO> findAll(int pageNumber, String sortBy, String sortOrder,String courseName, String nameTeacher);

    void updateCourse(long id, AddUpdateCourseDTO course);

    void createCourse(long idTeacher, AddUpdateCourseDTO course);

    void enrollCourse(EnrollCouseDTO enrollCouseDTO);

    PageResponse<CourseDTO> getCourseOfTeacher(int pageNumber, String sortBy, String sortOrder, String courseName,long idTeacher);

    PageResponse<CourseDTO> getCourseOfStudent(int pageNumber, String sortBy, String sortOrder, String courseName, long idStudent);
}
