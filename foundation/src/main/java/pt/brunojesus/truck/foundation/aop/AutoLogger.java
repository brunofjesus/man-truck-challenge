package pt.brunojesus.truck.foundation.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AutoLogger annotation.
 * 
 * When in a class logs all the calls for their public methods.
 * 
 * When in a method logs all the calls for that method
 * 
 * @see LoggerAOP
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLogger {
}
