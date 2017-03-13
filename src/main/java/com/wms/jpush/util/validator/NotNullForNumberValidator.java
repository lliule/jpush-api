package com.wms.jpush.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullForNumberValidator implements ConstraintValidator<NotNullForNumber, Number> {
    public void initialize(NotNullForNumber notNullForNumber) {
    }

    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if(number == null ){
            return false;
        }
        return true;
    }
}
