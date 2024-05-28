package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.AddTeacherDTO;
import com.onlinelearningsystem.dto.TeacherDTO;
import com.onlinelearningsystem.dto.UpdateTeacherDTO;
import com.onlinelearningsystem.model.Teacher;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.service.teacher.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    //API
    @GetMapping("/list")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('ADMIN')")
    public ResponseEntity <PageResponse<TeacherDTO>> getAllTeacher(
            @RequestParam(name ="pageNumber",required = false,defaultValue = "0") int pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(name ="nameTeacher", required = false, defaultValue = "") String nameTeacher
    ) {
        PageResponse<TeacherDTO> teacherPage = teacherService.findAll(pageNumber,sortBy,sortOrder,nameTeacher);
        return new ResponseEntity<>(teacherPage, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('TEACHER')")
    public void createTeacher(@RequestBody AddTeacherDTO teacher,@RequestParam long idAccount) {
         this.teacherService.createTeacher(teacher,idAccount);
    }

    @PutMapping("/update")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('TEACHER')")
    public ResponseEntity<?> updateTeacher(@RequestParam long id, @RequestBody UpdateTeacherDTO teacher) {
        teacherService.updateTeacher(id, teacher);
        return ResponseEntity.ok().body("Your profile is edited!");
    }

    @GetMapping("viewProfile")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('TEACHER')")
    public ResponseEntity<TeacherDTO> getTeacherProfile(@RequestParam("id") long id) {
        return ResponseEntity.ok().body(this.teacherService.getTeacherById(id));
    }



}
