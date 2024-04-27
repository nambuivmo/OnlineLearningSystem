package com.onlinelearningsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWordController {
    @GetMapping({"/","/helloWord"})
    public String helloWord(@RequestParam(value = "name",defaultValue="World", required =true) String name, Model model) {
        model.addAttribute("name", name);
        return "HTML/helloWord";
    }
}
