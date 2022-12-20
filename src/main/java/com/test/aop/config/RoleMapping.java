package com.test.aop.config;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleMapping {
    Role visitorRole() default Role.GUEST;
}
