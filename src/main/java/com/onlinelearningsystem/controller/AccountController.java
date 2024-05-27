package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.dto.AddAccountDTO;
import com.onlinelearningsystem.response.MessResponse;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;


    @GetMapping("")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('ADMIN')")
    public ResponseEntity<PageResponse<AccountDto>> findAll(
            @RequestParam(name ="pageNumber",required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(name ="searchByEmail", required = false, defaultValue = "") String email
    ){
        PageResponse<AccountDto> accountPage = accountService.findAll(pageNumber,sortBy,sortOrder,email);
        return new ResponseEntity<>(accountPage, HttpStatus.OK);
    }

    @PutMapping("/updateActive/")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('ADMIN')")
    public ResponseEntity<?> updateActive(@RequestParam("id") long id, @RequestParam("isBanned") boolean isBanned){
        this.accountService.updateActive(id, isBanned);
        return ResponseEntity.ok("Update active successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AddAccountDTO account) {
        this.accountService.register(account);
        return ResponseEntity.ok("Register successfully");
    }

    @GetMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        MessResponse mailRepsonse=accountService.forgotPassword(email);
        return ResponseEntity.ok(mailRepsonse);
    }

    @PutMapping("/changePassword")
    @PreAuthorize("@accountServiceImpl.getRoles().toString().contains('ADMIN')")
    public ResponseEntity<?> changePassword(@RequestParam("email") String email,
                                            @RequestParam("oldPassword") String oldPassword,
                                            @RequestParam("newPassword") String newPassword){
        MessResponse messResponse= accountService.changePassword(email,oldPassword,newPassword);
        return ResponseEntity.ok(messResponse);
    }

//    @GetMapping("/list")
//    public String listAccounts(Model model) {
//        List<AccountDto> accounts = accountService.findAll(); // Lấy danh sách account từ service
//        model.addAttribute("accounts", accounts);// Thêm danh sách account vào model
//        return "HTML/Admin/ViewListAccount"; // Trả về view HTML
//    }




}
