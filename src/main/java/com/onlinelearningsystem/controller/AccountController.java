package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.service.account.IAccountService;
import com.onlinelearningsystem.utils.AppConstants;
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
//    public ResponseEntity<Page<AccountDto>> findAll(Pageable pageable) {
//        Page<AccountDto> accountPage = accountService.findAll(pageable);
//        return new ResponseEntity<>(accountPage, HttpStatus.OK);
////        return ResponseEntity.ok().body(pageResponse);
//    }
    public ResponseEntity<PageResponse<AccountDto>> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok().body( accountService.findAll(pageNo, pageSize, sortBy,sortDir));
    }

    @PutMapping("/updateActive/")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Account> updateActive(@RequestParam("id") long id, @RequestParam("isBanned") boolean isBanned){
        return ResponseEntity.ok().body(accountService.updateActive(id, isBanned));
    }

    @GetMapping("/search/")
    @PreAuthorize("hasAuthority('admin:read')")
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
