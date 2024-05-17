package com.onlinelearningsystem.service.student;

import com.onlinelearningsystem.dto.StudentDTO;
import com.onlinelearningsystem.model.Student;

import java.util.List;

public interface IStudentService {

    //Get all student on system
    List<StudentDTO> findAll();


    Student createStudent(Student student);

    Student updateStudent(Long id,Student student);

    List<StudentDTO> getStudent(String firstName, String lastName);
}
