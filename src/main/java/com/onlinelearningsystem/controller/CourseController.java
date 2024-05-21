package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.model.Course;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    //API
    @PostMapping("/add")
    public ResponseEntity <?> createStudent(@RequestBody Course course) {
        try {
            this.courseService.createCourse(course);
            return ResponseEntity.ok().body("Course added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add course: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponse<CourseDTO>> getAllCourse(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam("courseName") String courseName,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        PageResponse<CourseDTO> courseDTO = courseService.findAll(pageNumber,sortBy,sortOrder,courseName,firstName, lastName);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/search/")
    public ResponseEntity<List<CourseDTO>> getCourse(@RequestParam("courseName") String courseName,@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return ResponseEntity.ok().body(this.courseService.getCourse(courseName, firstName, lastName));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCourse(@RequestParam("id") long id, @RequestBody Course course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.ok().body("Your profile is edited!");
    }

}
