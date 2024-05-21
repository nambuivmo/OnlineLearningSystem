package com.onlinelearningsystem.service.student;

import com.onlinelearningsystem.dto.StudentDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.response.PageResponse;

import java.util.List;

public interface IStudentService {

    //Get all student on system
    PageResponse<StudentDTO> findAll(int pageNumber, String sortBy, String sortOrder,String firstName, String lastName);


    Student createStudent(Student student);

    Student updateStudent(Long id,Student student);

    StudentDTO getStudent(long id);
}
