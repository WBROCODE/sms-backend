package net.javaguides.sms.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)          // can only go on methods
@Retention(RetentionPolicy.RUNTIME)  // Spring must see it while the app runs
public @interface LogMe {
}