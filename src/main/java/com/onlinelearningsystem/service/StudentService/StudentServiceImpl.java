package com.onlinelearningsystem.service.StudentService;

import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }
}
