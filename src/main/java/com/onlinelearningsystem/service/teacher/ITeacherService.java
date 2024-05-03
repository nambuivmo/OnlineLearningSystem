package com.onlinelearningsystem.service.teacher;

import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.model.Teacher;

import java.util.List;

public interface ITeacherService {

    List<TeacherDTO> findAll();

    Teacher updateTeacher(long id, Teacher teacher);

    Student createTeacher(Teacher teacher);

    List<TeacherDTO> getTeacher(String firstName, String lastName);
}
