package com.onlinelearningsystem.service.course;

import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.model.Course;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> findAll();

    List<CourseDTO> getCourse(String courseName, String firstName, String lastName);

    Course updateCourse(long id, Course course);

    void createCourse(Course course);
}
