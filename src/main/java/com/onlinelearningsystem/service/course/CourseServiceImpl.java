package com.onlinelearningsystem.service.course;

import com.onlinelearningsystem.dto.AddCourseDTO;
import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.dto.EnrollCouseDTO;
import com.onlinelearningsystem.model.Course;
import com.onlinelearningsystem.model.Enrollment;
import com.onlinelearningsystem.model.Payment;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.repository.CourseRepository;
import com.onlinelearningsystem.repository.EnrollmentRepository;
import com.onlinelearningsystem.repository.PaymentRepository;
import com.onlinelearningsystem.repository.StudentRepository;
import com.onlinelearningsystem.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Override
    public PageResponse<CourseDTO> findAll(int pageNumber, String sortBy, String sortOrder, String courseName, String nameTeacher) {
        int pageSize = 5;
        Pageable pageable;

        if (sortOrder.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }
        if (nameTeacher == null) {
            nameTeacher="";
        }else if(courseName == null){
            courseName="";
        }
        Page<CourseDTO> coursePage = courseRepository.findAllCourse(pageable,courseName,nameTeacher);
        PageResponse<CourseDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(coursePage.getContent());
        pageResponse.setTotalElements(coursePage.getTotalElements());
        pageResponse.setTotalPages(coursePage.getTotalPages());
        pageResponse.setPageNo(pageNumber);
        return pageResponse;
    }

    @Override
    public Course updateCourse(long id, Course course) {
        Optional<Course> courseDbOptional  = this.courseRepository.findById(id);
        if (courseDbOptional.isPresent()) {
            Course courseDb = courseDbOptional.get();
            courseDb.setName(course.getName());
            courseDb.setDescription(course.getDescription());
            return courseRepository.save(courseDb);
        }else{
            return null;
        }
    }

    @Transactional
    @Override
    public void createCourse(long teacherId, AddCourseDTO course) {
        if (course != null) {
            LocalDate dateNow = LocalDate.now();
            this.courseRepository.createCourse(course.getName(), dateNow, course.getDescription(), teacherId);
        }
    }

    @Override
    public void enrollCourse(EnrollCouseDTO enrollCouseDTO) {
        Optional<Student> student=studentRepository.findById(enrollCouseDTO.getIdStudent());
        Optional<Course> course=courseRepository.findById(enrollCouseDTO.getIdCourse());
        Optional<Payment> payment=paymentRepository.findById(enrollCouseDTO.getIdPayemnt());
        Enrollment e = new Enrollment();
        e.setStudent(student.get());
        e.setCourse(course.get());
        e.setPayment(payment.get());
        this.enrollmentRepository.save(e);
    }

    @Override
    public PageResponse<CourseDTO> getCourseOfTeacher(int pageNumber, String sortBy, String sortOrder, String courseName,long idTeacher) {
        int pageSize = 5;
        Pageable pageable;

        if (sortOrder.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }

        if(courseName == null){
            courseName="";
        }
        Page<CourseDTO> coursePage = courseRepository.getCourseOfTeacher(pageable,courseName,idTeacher);
        PageResponse<CourseDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(coursePage.getContent());
        pageResponse.setTotalElements(coursePage.getTotalElements());
        pageResponse.setTotalPages(coursePage.getTotalPages());
        pageResponse.setPageNo(pageNumber);
        return pageResponse;
    }

    @Override
    public PageResponse<CourseDTO> getCourseOfStudent(int pageNumber, String sortBy, String sortOrder, String courseName, long idStudent) {
        int pageSize = 5;
        Pageable pageable;

        if (sortOrder.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }

        if(courseName == null){
            courseName="";
        }
        Page<CourseDTO> coursePage = courseRepository.getCoursOfStudent(pageable,courseName,idStudent);
        PageResponse<CourseDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(coursePage.getContent());
        pageResponse.setTotalElements(coursePage.getTotalElements());
        pageResponse.setTotalPages(coursePage.getTotalPages());
        pageResponse.setPageNo(pageNumber);
        return pageResponse;
    }
}
