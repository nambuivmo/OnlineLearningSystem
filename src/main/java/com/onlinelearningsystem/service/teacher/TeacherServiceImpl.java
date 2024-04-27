package com.onlinelearningsystem.service.teacher;

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
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }
}
