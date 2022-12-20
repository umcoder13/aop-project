package com.test.aop.service;

import com.test.aop.config.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Service
public class AopService {

    public void guestMakeSession() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        if (request.getHeader("X-Client-ID").isEmpty()) {
            throw new RuntimeException("id is empty");
        }

        String header = request.getHeader("X-Client-ID");
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("id", header);
    }

    public void guestValidateSession() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        if (!request.getHeader("Role").equals(Role.USER.getRole())) {
            throw new RuntimeException("Role Does Not Match!");
        }

        if (request.getHeader("X-Client-ID").isEmpty()) {
            throw new RuntimeException("id is empty");
        }

        String header = request.getHeader("X-Client-ID");
        HttpSession httpSession = request.getSession(true);

        String id = (String) httpSession.getAttribute("id");

        if(!header.equals(id)) {
          log.error("fail!");
          throw new RuntimeException("id does not match!");
        }

        log.info("success!");
    }
}
