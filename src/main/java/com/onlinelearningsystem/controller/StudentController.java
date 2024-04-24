package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.repository.StudentRepository;
import com.onlinelearningsystem.service.StudentService.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService istudentservice;

    @GetMapping("/listStudent")
    public ResponseEntity <List<Student>> getAllStudent() {
        return ResponseEntity.ok().body(istudentservice.getAllStudent());
    }



}
