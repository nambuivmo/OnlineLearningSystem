package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.Response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.service.AccountService.IAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final IAccountService accountService;

    @PostMapping("/createNewAccount")
    public ResponseEntity<AccountResponse> addNewAccount(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam boolean isBanned ,
            @RequestParam long roleId) {
        Account savedAccount = accountService.createAccount(email,password,roleId,isBanned);
        AccountResponse response = new AccountResponse(savedAccount.getEmail(),
                savedAccount.getPassword(), savedAccount.getRoleAccount().getIdRole(),savedAccount.isBanned());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listAccount")
//    public ResponseEntity<List<Account>> getAllAccounts() {
//        List<Account> accounts = accountService.getAllAccount();
//        return ResponseEntity.ok(accounts);
//    }
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.FOUND);
    }

}
