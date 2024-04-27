package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.StudentInforListDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService istudentservice;

    //API
    @GetMapping("/list")
    public ResponseEntity <List<StudentInforListDTO>> getAllStudent() {
        return ResponseEntity.ok().body(istudentservice.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity < Student > createStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(this.istudentservice.createStudent(student));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity < Student > updateStudent(@PathVariable long id, @RequestBody Student student) {
        student.setId(id);
        return ResponseEntity.ok().body(this.istudentservice.updateStudent(student));
    }

    //View
//    @GetMapping("/list")
//    public String getAllStudent(Model model) {
//        List<StudentInforListDTO> students = istudentservice.findAll(); // Lấy danh sách account từ service
//        model.addAttribute("students", students); // Thêm danh sách account vào model
//        return "HTML/Admin/ViewListStudent"; // Trả về view HTML
//    }





}
