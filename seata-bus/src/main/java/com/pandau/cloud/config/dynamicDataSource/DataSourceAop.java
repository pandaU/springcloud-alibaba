package com.pandau.cloud.config.dynamicDataSource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DataSourceAop {
    @Before("@annotation(com.pandau.cloud.config.dynamicDataSource.Db)")
    public void setDataSource(JoinPoint point) {
        log.info("切换数据库");
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Db db = (Db)targetMethod.getAnnotation(Db.class);
        if (db == null){
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.MASTER);
        }else {
            String value = db.value();
            if (value.equals("master")){
                DataSourceType.setDataBaseType(DataSourceType.DataBaseType.MASTER);
            }else {
                DataSourceType.setDataBaseType(DataSourceType.DataBaseType.SLAVE);
            }
        }
    }
}
