package com.bs.spring.common.websocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.OracleJdbc2SQLInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


//소켓통신을 할 수 있는 클래스

@Slf4j
public class ChattingServer extends TextWebSocketHandler {

    private Map<String,WebSocketSession> clients= new HashMap<>();

//    private Map<String, List<Map<String, WebSocketSession>>>

    @Autowired
    private ObjectMapper mapper;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("사용자가 접속했습니다.");

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("전송된 데이터 : {} ", message.getPayload());
        Message msg = mapper.readValue(message.getPayload(), Message.class);
        switch(msg.getType()){
            case "open" : addClient(session,msg);break;
            case "chat" : sendChatMessage(msg);break;

        }
    }


    private void sendChatMessage(Message msg) {
        if (msg.getReceiver() == null || msg.getReceiver().equals("")) {
            try {
                final String strMsg = mapper.writeValueAsString(msg);
                clients.values().forEach(client -> {
                    try {
                        client.sendMessage(new TextMessage(strMsg));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                final String strMsg = mapper.writeValueAsString(msg);
               if(clients.get(msg.getReceiver())!=null) clients.get(msg.getReceiver()).sendMessage(new TextMessage(strMsg));
               if(clients.get(msg.getSender())!=null) clients.get(msg.getSender()).sendMessage(new TextMessage(strMsg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private void addClient(WebSocketSession session,Message msg){
        clients.put(msg.getSender(),session);
        sendMessage(Message.builder()
                .type("open")
                .msg(msg.getSender()+"님이 참여했습니다.")
                .build());
    }

    private void sendMessage(Message msg) {
        try {
            final String strMsg = mapper.writeValueAsString(msg);
            clients.values().forEach(client -> {
                try {
                    client.sendMessage(new TextMessage(strMsg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
