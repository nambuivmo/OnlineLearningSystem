package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.dto.StudentDTO;
import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.model.Student;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity <PageResponse<StudentDTO>> getAllStudent(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(name ="firstName") String firstName,
            @RequestParam(name ="lastName") String lastName
    ) {
        PageResponse<StudentDTO> studentPage = istudentservice.findAll(pageNumber,sortBy,sortOrder,firstName,lastName);
        return new ResponseEntity<>(studentPage, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity < Student > createStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(this.istudentservice.createStudent(student));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable long id, @RequestBody Student student) {
        istudentservice.updateStudent(id, student);
        return ResponseEntity.ok().body("Your profile is edited!");
    }

    @GetMapping("viewProfile")
    public ResponseEntity<StudentDTO> getTeacherProfile(@RequestParam("id") long id) {
        return ResponseEntity.ok().body(this.istudentservice.getStudent(id));
    }

    //View
//    @GetMapping("/list")
//    public String getAllStudent(Model model) {
//        List<StudentInforListDTO> students = istudentservice.findAll(); // Lấy danh sách account từ service
//        model.addAttribute("students", students); // Thêm danh sách account vào model
//        return "HTML/Admin/ViewListStudent"; // Trả về view HTML
//    }

}
