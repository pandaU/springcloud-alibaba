package com.pandau.cloud.validates;

import com.pandau.cloud.entities.PayInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class EqValidator  implements ConstraintValidator<EqOptions, PayInfo> {

    @Override
    public boolean isValid(PayInfo info, ConstraintValidatorContext constraintValidatorContext) {
            String serial = info.getSerial();
            // 这里只是做示例用，所以简单实用了equals进行对比，实际使用可以根据业务要求做更多拓展
        boolean b = serial.contains("hello");
        if (b){
           return true;
        }
        return false;
    }
}
