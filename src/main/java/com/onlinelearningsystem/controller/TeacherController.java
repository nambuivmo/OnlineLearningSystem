package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.service.teacher.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    //API
    @GetMapping("/list")
    public ResponseEntity <PageResponse<TeacherDTO>> getAllTeacher(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        PageResponse<TeacherDTO> teacherPage = teacherService.findAll(pageNumber,sortBy,sortOrder,firstName,lastName);
        return new ResponseEntity<>(teacherPage, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity <Teacher> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok().body(this.teacherService.createTeacher(teacher));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTeacher(@RequestParam long id, @RequestBody Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return ResponseEntity.ok().body("Your profile is edited!");
    }

    @GetMapping("viewProfile")
    public ResponseEntity<TeacherDTO> getTeacherProfile(@RequestParam("id") long id) {
        return ResponseEntity.ok().body(this.teacherService.getTeacherById(id));
    }


}
