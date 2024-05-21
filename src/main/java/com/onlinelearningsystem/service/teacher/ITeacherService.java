package com.onlinelearningsystem.service.teacher;

import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.response.PageResponse;

import java.util.List;

public interface ITeacherService {

    PageResponse<TeacherDTO> findAll(int pageNumber, String sortBy, String sortOrder,String firstName, String lastName);

    Teacher updateTeacher(long id, Teacher teacher);

    Teacher createTeacher(Teacher teacher);

    TeacherDTO getTeacherById(long id);
}
