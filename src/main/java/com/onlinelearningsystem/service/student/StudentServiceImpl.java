package com.onlinelearningsystem.service.student;

import com.onlinelearningsystem.dto.StudentDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.repository.StudentRepository;
import com.onlinelearningsystem.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public PageResponse<StudentDTO> findAll(int pageNumber,String sortBy,String sortOrder,String firstName,String lastName) {
        int pageSize = 5;
        Pageable pageable;

        if (sortOrder.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }
        if (firstName == null) {
            firstName = "";
        }
        if (lastName == null) {
            lastName = "";
        }
        Page<StudentDTO> studentPage = studentRepository.findAllStudent(pageable,firstName,lastName);
        PageResponse<StudentDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(studentPage.getContent());
        pageResponse.setTotalElements(studentPage.getTotalElements());
        pageResponse.setTotalPages(studentPage.getTotalPages());
        pageResponse.setPageNo(pageNumber);
        return pageResponse;
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
    public StudentDTO getStudent(long id) {
        return  studentRepository.getStudent(id);
    }

}
