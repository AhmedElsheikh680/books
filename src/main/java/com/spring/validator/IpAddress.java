package com.spring.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IpAddressImpl.class })
public @interface IpAddress {


    String message() default "{validation.constraints.ip-address.message}";

    Class<?>[] groups() default { };


    Class<? extends Payload>[] payload() default { };

}


