package com.work.note.controller;

import com.work.note.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import java.util.Date;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author LiuZhao
 * @Date 2021/11/9 10:17
 * @Version 1.0
 **/
@RestController
@EnableScheduling
public class TestController {

    @Autowired
    private WebSocketServer webSocketServer;

    @Scheduled(cron = "*/20 * * * * ?")
    public void sendMessageToUsers(){
        webSocketServer.send2AllUsers(new TextMessage("现在时间是:"+new Date()));
    }
}
