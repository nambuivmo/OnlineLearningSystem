package com.onlinelearningsystem.service.course;

import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.model.Course;
import com.onlinelearningsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseDTO> findAll() {
        return this.courseRepository.findAllCourse();
    }

    @Override
    public List<CourseDTO> getCourse(String courseName, String firstName, String lastName) {
        if (firstName == null) {
            firstName="";
        }else if (lastName == null) {
            lastName="";
        }else if(courseName == null){
            courseName="";
        }
        return this.courseRepository.getCourse(courseName,firstName, lastName);
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

    @Override
    public void createCourse(Course course) {
//        if (course != null) {
//            LocalDate dateNow = LocalDate.now();
//            // Truyền id người tạo từ session hoặc token
//            long teacherId = getLoggedInUserId(); // Phương thức này phải được triển khai để lấy id của người dùng đăng nhập
//            this.courseRepository.createCourse(course.getName(), dateNow, course.getDescription(), teacherId);
//        }
    }
}
