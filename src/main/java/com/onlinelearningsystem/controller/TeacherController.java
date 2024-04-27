package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.service.teacher.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/list")
    public ResponseEntity<List<Teacher>> findAll() {
        return ResponseEntity.ok().body(teacherService.findAll());
    }

}
