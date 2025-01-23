package com.bs.spring.common.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String type;
    private String sender;
    private String receiver;
    private String msg;
    private String room;
}
