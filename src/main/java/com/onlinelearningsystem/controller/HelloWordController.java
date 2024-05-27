package com.onlinelearningsystem.controller;

import com.onlinelearningsystem.service.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class HelloWordController {
    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping({"/","/helloWord"})
    public String helloWord(@RequestParam(value = "name",defaultValue="World", required =true) String name, Model model) {
        model.addAttribute("name", name);
        return "HTML/helloWord";
    }
}
