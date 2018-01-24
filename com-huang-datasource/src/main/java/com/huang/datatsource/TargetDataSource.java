package com.huang.datatsource;

import java.lang.annotation.*;

/**
 * @author JeffreyHy
 * @date Created by  2018/1/24 18:30
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
	String name() default "";
}