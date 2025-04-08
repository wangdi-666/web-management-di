package com.didi.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //标识这个注解在哪生效
@Retention(RetentionPolicy.RUNTIME) //标识这个注解什么时候生效
public @interface Log {
}
