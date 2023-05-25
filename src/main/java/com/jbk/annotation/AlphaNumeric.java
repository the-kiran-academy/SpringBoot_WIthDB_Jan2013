package com.jbk.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
//@Pattern(regexp = "^[a-zA-Z]*$",message = "Invalid Product Name")
@Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$",message = "Invalid Product Name")
@Constraint(validatedBy =  {})
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AlphaNumeric {
String message() default "";
Class<?>[] groups()default{};
Class<? extends Payload>[]payload()default{};
}
