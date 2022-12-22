package com.test.aop.aop;

import com.test.aop.service.AopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SessionAspect {

    private final AopService aopService;

    @Before("@annotation(com.test.aop.config.SessionCheck)")
    public void findRoleInSession(JoinPoint joinPoint) throws Throwable {
        aopService.guestValidateSession();
    }

    @Before("@annotation(com.test.aop.config.PlusRole)")
    public void plusRole(JoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Object obj = args[0];
        Field[] fields = obj.getClass().getDeclaredFields();
        log.info(Arrays.toString(fields));
        for (Field field : fields) {
            if (field.getName().equals("one") || field.getName().equals("two")) {
                field.setAccessible(true);
                String value = (String) field.get(obj);
                value += " Everyone!";
                field.set(obj, value);
            }
        }
    }
}
