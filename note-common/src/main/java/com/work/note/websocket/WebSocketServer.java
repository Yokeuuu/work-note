package com.work.note.websocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collection;

/**
 * @ClassName WebSocketServer
 * @Description WebSocketServer
 * @Author LiuZhao
 * @Date 2021/9/3 14:26
 * @Version 1.0
 **/
@Slf4j
@Component
public class WebSocketServer extends TextWebSocketHandler {

    /**
     * @param session
     * @return void
     * @description websocket用户连接成功
     * @author LiuZhao
     * @date 2021/10/25 14:53
     **/
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 用户连接成功，放入在线用户缓存
        WsSessionManager.add(session.getId(), session);
    }

    /**
     * @param session, message
     * @return void
     * @description websocket接收消息
     * @author LiuZhao
     * @date 2021/10/25 14:55
     **/
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("============================sessionId:"+session.getId());
        System.out.println("============================接收到客户端{}传来的消息"+message.getPayload());
        log.debug("============================接收到客户端{}传来的消息", message.getPayload());

    }

    /**
     * @param session, message
     * @return void
     * @description websocket关闭连接
     * @author LiuZhao
     * @date 2021/10/25 14:56
     **/
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

        WsSessionManager.removeAndClose(session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable e) {
        //异常时触发
        log.error(e.getMessage(), e);
        WsSessionManager.removeAndClose(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * @param userId, message
     * @return void
     * @description websocket给用户发送消息
     * @author LiuZhao
     * @date 2021/10/25 14:59
     **/
    public void send2User(String userId, TextMessage message) {
        if (StringUtils.isBlank(userId)) {
            return;
        }

        WebSocketSession session = WsSessionManager.get(userId);
        if (session != null) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * @param message
     * @return void
     * @description websocket给所有用户发送消息
     * @author LiuZhao
     * @date 2021/10/25 14:59
     **/
    public void send2AllUsers(TextMessage message) {
        Collection<WebSocketSession> sessions = WsSessionManager.getAll();
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
