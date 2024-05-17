package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.service.teacher.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity <List<TeacherDTO>> getAllTeacher() {
        return ResponseEntity.ok().body(teacherService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity <Student> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok().body(this.teacherService.createTeacher(teacher));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable long id, @RequestBody Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return ResponseEntity.ok().body("Your profile is edited!");
    }

    @GetMapping("/search/")
    public ResponseEntity<List<TeacherDTO>> getTeacher(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return ResponseEntity.ok().body(this.teacherService.getTeacher(firstName, lastName));
    }

}
