package com.onlinelearningsystem.service.teacher;


import com.onlinelearningsystem.dto.AddTeacherDTO;
import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.repository.AccountRepository;
import com.onlinelearningsystem.repository.TeacherRepository;
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
public class TeacherServiceImpl implements ITeacherService{

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public PageResponse<TeacherDTO> findAll(int pageNumber, String sortBy, String sortOrder,String firstName, String lastName) {
        int pageSize = 5;
        Pageable pageable;
        if (sortOrder.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }
        if (firstName == null) {
            firstName="";
        }else if (lastName == null) {
            lastName="";
        }
        Page<TeacherDTO> teacherDTO = teacherRepository.findAllTeacher(pageable,firstName,lastName);
        PageResponse<TeacherDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(teacherDTO.getContent());
        pageResponse.setTotalElements(teacherDTO.getTotalElements());
        pageResponse.setTotalPages(teacherDTO.getTotalPages());
        pageResponse.setPageNo(pageNumber);
        return pageResponse;
    }

    @Override
    public Teacher updateTeacher(long id, Teacher teacher) {
        Teacher teacherId= teacherRepository.findById(id).get();
        teacherId.setFirstName(teacher.getFirstName());
        teacherId.setLastName(teacher.getLastName());
        teacherId.setDob(teacher.getDob());
        teacherId.setGender(teacher.isGender());
        teacherId.setAddress(teacher.getAddress());
        teacherId.setPhoneNumber(teacher.getPhoneNumber());
        return teacherRepository.save(teacherId);
    }

    @Override
    public void createTeacher(AddTeacherDTO teacher,long idAccount) {
        Teacher addTeacher= new Teacher();
        Optional<Account> account = accountRepository.findById(idAccount);
        if (account.isPresent()){
            addTeacher.setFirstName(teacher.getFirstName()); // Sửa tên phương thức
            addTeacher.setLastName(teacher.getLastName());   // Sửa tên phương thức
            addTeacher.setDob(teacher.getDob());             // Sửa tên phương thức
            addTeacher.setPhoneNumber(teacher.getPhoneNumber()); // Sửa tên phương thức
            addTeacher.setGender(teacher.isGender());        // Sửa tên phương thức
            addTeacher.setAddress(teacher.getAddress());     // Sửa tên phương thức
            addTeacher.setAccount(account.get());
            teacherRepository.save(addTeacher);
        }else {
            throw new EntityNotFoundException("Account not found with id: " + idAccount);
        }
    }

    @Override
    public TeacherDTO getTeacherById(long id) {
        return teacherRepository.getTeacher(id);
    }
}
