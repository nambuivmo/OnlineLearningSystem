package com.onlinelearningsystem.service.course;

import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.model.Course;
import com.onlinelearningsystem.response.PageResponse;

import java.util.List;

public interface ICourseService {
    PageResponse<CourseDTO> findAll(int pageNumber, String sortBy, String sortOrder,String courseName, String firstName, String lastName);

    List<CourseDTO> getCourse(String courseName, String firstName, String lastName);

    Course updateCourse(long id, Course course);

    void createCourse(Course course);
}
