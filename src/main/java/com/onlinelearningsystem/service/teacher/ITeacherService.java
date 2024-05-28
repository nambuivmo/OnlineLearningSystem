package com.onlinelearningsystem.service.teacher;

import com.onlinelearningsystem.dto.AddTeacherDTO;
import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.dto.UpdateTeacherDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.response.PageResponse;

import java.util.List;

public interface ITeacherService {

    PageResponse<TeacherDTO> findAll(int pageNumber, String sortBy, String sortOrder,String nameTeacher);

    void updateTeacher(long id, UpdateTeacherDTO teacher);

    void createTeacher(AddTeacherDTO teacher,long idAccount);

    TeacherDTO getTeacherById(long id);
}
