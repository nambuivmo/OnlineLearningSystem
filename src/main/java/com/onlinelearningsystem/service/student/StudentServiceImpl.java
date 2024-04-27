package com.onlinelearningsystem.service.student;

import com.onlinelearningsystem.dto.StudentInforListDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<StudentInforListDTO> findAll() {
        return this.studentRepository.findAllStudent();
    }

    @Override
    public Student createStudent(Student student) {
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        return null;
    }
}
