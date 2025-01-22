package com.bs.spring.common.myannotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 생명주기 RUNTIME에 이용
@Documented
public @interface MyAnnotation {

}
