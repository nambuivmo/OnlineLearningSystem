package com.onlinelearningsystem.service.course;

import com.onlinelearningsystem.dto.AddCourseDTO;
import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.model.Course;
import com.onlinelearningsystem.repository.CourseRepository;
import com.onlinelearningsystem.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public PageResponse<CourseDTO> findAll(int pageNumber, String sortBy, String sortOrder, String courseName, String firstName, String lastName) {
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
        }else if(courseName == null){
            courseName="";
        }
        Page<CourseDTO> coursePage = courseRepository.findAllCourse(pageable,courseName,firstName,lastName);
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
}
