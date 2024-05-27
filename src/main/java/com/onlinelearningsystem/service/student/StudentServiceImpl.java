package com.onlinelearningsystem.service.student;

import com.onlinelearningsystem.dto.AddStudentDTO;
import com.onlinelearningsystem.dto.SearchStudentDTO;
import com.onlinelearningsystem.dto.StudentDTO;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.repository.AccountRepository;
import com.onlinelearningsystem.repository.StudentRepository;
import com.onlinelearningsystem.response.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public PageResponse<SearchStudentDTO> findAll(int pageNumber,String sortBy,String sortOrder,String nameStudent) {
        int pageSize = 5;
        Pageable pageable;

        if (sortOrder.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }
        if (nameStudent == null) {
            nameStudent = "";
        }
        Page<SearchStudentDTO> studentPage = studentRepository.findAllStudent(pageable,nameStudent);
        PageResponse<SearchStudentDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(studentPage.getContent());
        pageResponse.setTotalElements(studentPage.getTotalElements());
        pageResponse.setTotalPages(studentPage.getTotalPages());
        pageResponse.setPageNo(pageNumber);
        return pageResponse;
    }

    @Override
    public void createStudent(AddStudentDTO student,long idAccount) {
        Student addStudent= new Student();
        Optional<Account> account = accountRepository.findById(idAccount);
        if (account.isPresent()){
            addStudent.setFirstName(student.getFirstName()); // Sửa tên phương thức
            addStudent.setLastName(student.getLastName());   // Sửa tên phương thức
            addStudent.setDob(student.getDob());             // Sửa tên phương thức
            addStudent.setPhoneNumber(student.getPhoneNumber()); // Sửa tên phương thức
            addStudent.setGender(student.isGender());        // Sửa tên phương thức
            addStudent.setAddress(student.getAddress());     // Sửa tên phương thức
            addStudent.setAccount(account.get());
            studentRepository.save(addStudent);
        }else {
            throw new EntityNotFoundException("Account not found with id: " + idAccount);
        }
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
