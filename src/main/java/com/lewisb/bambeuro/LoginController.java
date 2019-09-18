package com.lewisb.bambeuro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String loginInfo() {
        return "Enter details to below to login";
    }
}
