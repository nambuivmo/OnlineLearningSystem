package com.onlinelearningsystem.service.student;

import com.onlinelearningsystem.dto.AddStudentDTO;
import com.onlinelearningsystem.dto.SearchStudentDTO;
import com.onlinelearningsystem.dto.StudentDTO;
import com.onlinelearningsystem.dto.UpdateStudentDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.response.PageResponse;

import java.util.List;

public interface IStudentService {

    //Get all student on system
    PageResponse<SearchStudentDTO> findAll(int pageNumber, String sortBy, String sortOrder, String nameStudent);

    void createStudent(AddStudentDTO student,long idAccount);

    void updateStudent(Long id, UpdateStudentDTO student);

    StudentDTO getStudent(long id);
}
