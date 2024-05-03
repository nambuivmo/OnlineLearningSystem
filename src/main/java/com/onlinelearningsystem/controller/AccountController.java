package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/list")
    public ResponseEntity<List<AccountDto>> findAll() {
        return ResponseEntity.ok().body(accountService.findAll());
    }

    @PutMapping("/updateActive/")
    public ResponseEntity<Account> updateActive(@RequestParam("id") long id, @RequestParam("isBanned") boolean isBanned){
        return ResponseEntity.ok().body(accountService.updateActive(id, isBanned));
    }

    @GetMapping("/search/")
    public ResponseEntity<List<AccountDto>> getAccount(@RequestParam("email") String email){
        return ResponseEntity.ok().body(accountService.getAccount(email));
    }

//    @GetMapping("/list")
//    public String listAccounts(Model model) {
//        List<AccountDto> accounts = accountService.findAll(); // Lấy danh sách account từ service
//        model.addAttribute("accounts", accounts);// Thêm danh sách account vào model
//        return "HTML/Admin/ViewListAccount"; // Trả về view HTML
//    }




}
