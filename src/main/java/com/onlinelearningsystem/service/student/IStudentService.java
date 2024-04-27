package com.onlinelearningsystem.service.student;

import com.onlinelearningsystem.dto.StudentInforListDTO;
import com.onlinelearningsystem.model.Student;

import java.util.List;

public interface IStudentService {

    //Get all student on system
    List<StudentInforListDTO> findAll();


    Student createStudent(Student student);

    Student updateStudent(Student student);
}
