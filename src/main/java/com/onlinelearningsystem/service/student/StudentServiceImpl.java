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
    public Student updateStudent(Long id, Student student) {
        Student studentId= studentRepository.findById(id).get();
        studentId.setFirstName(student.getFirstName());
        studentId.setLastName(student.getLastName());
        studentId.setDob(student.getDob());
        studentId.setGender(student.isGender());
        studentId.setAddress(student.getAddress());
        studentId.setPhoneNumber(student.getPhoneNumber());
        return studentRepository.save(studentId);
    }

    @Override
    public List<StudentInforListDTO> getStudent(String firstName, String lastName) {
        if (firstName == null) {
             firstName="";
        }else if (lastName == null) {
            lastName="";
        }
        return this.studentRepository.getUser(firstName, lastName);
    }
}
