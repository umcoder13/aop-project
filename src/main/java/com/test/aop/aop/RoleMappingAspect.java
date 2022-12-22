package com.test.aop.aop;

import com.test.aop.config.Role;
import com.test.aop.config.RoleMapping;
import com.test.aop.service.AopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RoleMappingAspect {


    private final AopService aopService;

    @Around("@annotation(com.test.aop.config.RoleMapping)")
    public Object sayRole(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Role role = signature.getMethod().getDeclaredAnnotation(RoleMapping.class).visitorRole();
        log.info("AOP Success!");
        log.info("Role is " + role);

        if (role.equals(Role.GUEST)) {
            aopService.guestMakeSession();
            return pjp.proceed();
        }

        if (role.equals(Role.USER)) {
            aopService.guestValidateSession();
            return pjp.proceed();
        }

        return pjp.proceed();
    }
}
