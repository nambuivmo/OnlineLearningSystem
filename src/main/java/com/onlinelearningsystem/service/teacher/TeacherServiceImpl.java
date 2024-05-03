package com.onlinelearningsystem.service.teacher;

import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService{

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository.findAllTeacher();
    }

    @Override
    public Teacher updateTeacher(long id, Teacher teacher) {
        Teacher teacherId= teacherRepository.findById(id).get();
        teacherId.setFirstName(teacher.getFirstName());
        teacherId.setLastName(teacher.getLastName());
        teacherId.setDob(teacher.getDob());
        teacherId.setGender(teacher.getGender());
        teacherId.setAddress(teacher.getAddress());
        teacherId.setPhoneNumber(teacher.getPhoneNumber());
        return teacherRepository.save(teacherId);
    }

    @Override
    public Student createTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public List<TeacherDTO> getTeacher(String firstName, String lastName) {
        if (firstName == null) {
            firstName="";
        }else if (lastName == null) {
            lastName="";
        }
        return this.teacherRepository.getTeacher(firstName, lastName);
    }
}
