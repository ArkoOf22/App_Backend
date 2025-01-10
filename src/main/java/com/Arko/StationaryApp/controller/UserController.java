package com.Arko.StationaryApp.controller;

import com.Arko.StationaryApp.model.User;
import com.Arko.StationaryApp.service.UserService;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    //To encode the password we are using BCryptPasswordEncoder
   // private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService)
    {
        this.userService=userService;
        //this.bCryptPasswordEncoder=new BCryptPasswordEncoder();
    }

    //Setting up the sign up API
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user){
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setRole("USER");
        userService.saveUser(user);
        return ResponseEntity.ok("User is registered successfully");
    }

    //Setting up the Login API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        return userService.findByUsername(user.getUsername())
                .filter(existingUser -> user.getPassword().equals(existingUser.getPassword()))
                .map(existingUser -> ResponseEntity.ok("Login is successful!!"))
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}
