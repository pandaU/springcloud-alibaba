package com.pandau.cloud.config.dynamicDataSource;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Db {
    String value() default "master";
}
