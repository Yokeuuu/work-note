package com.work.note.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName EmailProperties
 * @Description 邮件属性类
 * @Author LiuZhao
 * @Date 2021/7/15 17:34
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {

    private String host;

    private String username;

    private String password;
}
