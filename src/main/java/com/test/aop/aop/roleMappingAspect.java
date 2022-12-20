package com.test.aop.aop;

import com.test.aop.config.RoleMapping;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class roleMappingAspect {

    @Around("@annotation(com.test.aop.config.RoleMapping)")
    public Object sayRole(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String role = signature.getMethod().getDeclaredAnnotation(RoleMapping.class).visitorRole().getRole();
        log.info("AOP Success!");
        log.info("Role is " + role);

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        if (request.getHeader("X-Client-ID").isEmpty()) {
            throw new RuntimeException("id is empty");
        }

        String header = request.getHeader("X-Client-ID");

        log.info("Header Name is " + header);
        return pjp.proceed();
    }
}
