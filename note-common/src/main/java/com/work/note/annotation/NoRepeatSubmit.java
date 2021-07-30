package com.work.note.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName RepeatToken
 * @Description TODO
 * @Author LiuZhao
 * @Date 2021/7/27 15:14
 * @Version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 锁过期的时间
     * */
    int expires() default 5;

}
