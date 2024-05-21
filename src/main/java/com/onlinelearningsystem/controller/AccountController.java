package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/account")
//@PreAuthorize("hasRole('ADMIN')")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @GetMapping("")
//    @PreAuthorize("hasAuthority('admin:read')")
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
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Account> updateActive(@RequestParam("id") long id, @RequestParam("isBanned") boolean isBanned){
        return ResponseEntity.ok().body(accountService.updateActive(id, isBanned));
    }

//    @GetMapping("/list")
//    public String listAccounts(Model model) {
//        List<AccountDto> accounts = accountService.findAll(); // Lấy danh sách account từ service
//        model.addAttribute("accounts", accounts);// Thêm danh sách account vào model
//        return "HTML/Admin/ViewListAccount"; // Trả về view HTML
//    }




}
