package com.work.note.websocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @ClassName WebSocketInterceptor
 * @Description websocket拦截器
 * @Author LiuZhao
 * @Date 2021/11/8 16:18
 * @Version 1.0
 **/
@Component
@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * @description 在握手之前执行该方法,继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
     * @param request, response, wsHandler, attributes
     * @return boolean
     * @author LiuZhao
     * @date 2021/11/8 16:20
     **/
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, org.springframework.web.socket.WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        String token = request.getHeaders().getFirst("token");
//        if(StringUtils.isNotBlank(token)){
//            attributes.put("token",token);
//        }
//        return StringUtils.isNotBlank(token);
        return true;
    }

    /**
     * @description 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头
     * @param request, response, wsHandler, exception
     * @return void
     * @author LiuZhao
     * @date 2021/11/8 16:20
     **/
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, org.springframework.web.socket.WebSocketHandler wsHandler, Exception exception) {
        log.debug("=========================握手结束！");
    }
}
