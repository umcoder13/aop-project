package com.test.aop.controller;

import com.test.aop.config.Role;
import com.test.aop.config.RoleMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @GetMapping("/permission")
    @RoleMapping(visitorRole = Role.ADMIN)
    public ResponseEntity<String> getPermission() {
        return ResponseEntity.ok("ok");
    }
}
