package com.Arko.StationaryApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginPage {

    @GetMapping("/")
    public String loginPage(HttpServletRequest request){
        return "Welcome to the Stationary App " + request.getSession().getId();
    }
}
