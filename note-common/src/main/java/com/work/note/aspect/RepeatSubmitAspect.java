package com.work.note.aspect;

import com.work.note.annotation.NoRepeatSubmit;
import com.work.note.response.Result;
import com.work.note.util.RedisUtil;
import com.work.note.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {


    @Autowired
    RedisUtil redisUtil;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around("@annotation(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        int expires = noRepeatSubmit.expires();

        HttpServletRequest request = RequestUtils.getRequest();
        Assert.notNull(request, "request can not null!");

        // 此处可以用token或者JSessionId
        String token = request.getHeader("Authorization");
        String path = request.getServletPath();
        String key = token + path;

        if(redisUtil.getExpire(key)>0){
            log.error("==============重复操作,请5s后重试!");
            return Result.error().setMessage("重复操作,请5s后重试!");
        }else{
            redisUtil.set(key,"",expires);
            return pjp.proceed();
        }
    }

}