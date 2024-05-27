package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.AddCourseDTO;
import com.onlinelearningsystem.dto.CourseDTO;
import com.onlinelearningsystem.dto.EnrollCouseDTO;
import com.onlinelearningsystem.model.Course;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    //API
    @PostMapping("/add")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('TEACHER')")
    public ResponseEntity <?> createStudent(@RequestParam("idTeacher") long idTeacher, @RequestBody AddCourseDTO courseDTO) {

            this.courseService.createCourse(idTeacher,courseDTO);
            return ResponseEntity.ok().body("Course added successfully!");

    }

    @GetMapping("/list")
    public ResponseEntity<PageResponse<CourseDTO>> getAllCourse(
            @RequestParam(name ="pageNumber",required = false,defaultValue = "0") int pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(name ="courseName", required = false, defaultValue = "") String courseName,
            @RequestParam(name ="nameTeacher", required = false, defaultValue = "") String nameTeacher
    ){
        PageResponse<CourseDTO> courseDTO = courseService.findAll(pageNumber,sortBy,sortOrder,courseName,nameTeacher);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('TEACHER')")
    public ResponseEntity<?> updateCourse(@RequestParam("id") long id, @RequestBody Course course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.ok().body("Your course is edited!");
    }

    @PostMapping("/enrollCourse")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('STUDENT')")
    public ResponseEntity <?> createStudent(@RequestBody EnrollCouseDTO enrollCouseDTO) {
        this.courseService.enrollCourse(enrollCouseDTO);
        return ResponseEntity.ok().body("Successfully!");
    }


    @GetMapping("/courseOfTeacher")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('TEACHER')")
    public ResponseEntity<PageResponse<CourseDTO>> getCourseOfTeacher(
            @RequestParam(name ="pageNumber",required = false,defaultValue = "0") int pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(name ="courseName", required = false, defaultValue = "") String courseName,
            @RequestParam(name="idTeacher", required = true )long idTeacher
    ){
        PageResponse<CourseDTO> courseDTO = courseService.getCourseOfTeacher(pageNumber,sortBy,sortOrder,courseName,idTeacher);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/courseOfStudent")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('STUDENT')")
    public ResponseEntity<PageResponse<CourseDTO>> getCourseOfStudent(
            @RequestParam(name ="pageNumber",required = false,defaultValue = "0") int pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(name ="courseName", required = false, defaultValue = "") String courseName,
            @RequestParam(name="idStudent", required = true ) long idStudent
    ){
        PageResponse<CourseDTO> courseDTO = courseService.getCourseOfStudent(pageNumber,sortBy,sortOrder,courseName,idStudent);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

}
