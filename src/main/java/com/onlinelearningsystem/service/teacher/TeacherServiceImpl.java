package com.onlinelearningsystem.service.teacher;


import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.repository.TeacherRepository;
import com.onlinelearningsystem.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

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
    public Teacher createTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public TeacherDTO getTeacherById(long id) {
        return teacherRepository.getTeacher(id);
    }
}
