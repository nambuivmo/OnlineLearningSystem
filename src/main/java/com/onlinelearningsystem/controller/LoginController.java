package com.onlinelearningsystem.controller;


import com.onlinelearningsystem.dto.LoginDTO;
import com.onlinelearningsystem.response.LoginResponse;
import com.onlinelearningsystem.service.account.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private final IAccountService accountService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = accountService.login(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

//    @GetMapping("")
//    public ResponseEntity<String> register(
//    ) {
//        return ResponseEntity.ok("Hello World");
//    }

}
