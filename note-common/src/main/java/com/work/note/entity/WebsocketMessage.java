package com.work.note.entity;

import lombok.Data;

/**
 * @ClassName WebsocketMessage
 * @Description WebsocketMessage
 * @Author LiuZhao
 * @Date 2021/9/3 9:44
 * @Version 1.0
 **/
@Data
public class WebsocketMessage {

    private String data;

    /** 发送的目的地 */
    private String destination;
}
