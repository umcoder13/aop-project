package com.test.aop.controller;

import com.test.aop.config.PlusRole;
import com.test.aop.config.Role;
import com.test.aop.config.RoleMapping;
import com.test.aop.config.SessionCheck;
import com.test.aop.dto.ManyDto;
import com.test.aop.dto.OneDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AopController {

    @GetMapping("/permission")
    @RoleMapping
    public ResponseEntity<String> getPermission() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/validate")
    @RoleMapping(visitorRole = Role.USER)
    public ResponseEntity<String > postValidate() {
        return ResponseEntity.ok("ok");
    }

    @SessionCheck
    @PlusRole
    @PostMapping("/one")
    public ResponseEntity<OneDto> postOne(@RequestBody OneDto dto) {
        return ResponseEntity.ok(dto);
    }

    @SessionCheck
    @PlusRole
    @PostMapping("/many")
    public ResponseEntity<ManyDto> postOne(@RequestBody ManyDto dto) {
        return ResponseEntity.ok(dto);
    }
}
