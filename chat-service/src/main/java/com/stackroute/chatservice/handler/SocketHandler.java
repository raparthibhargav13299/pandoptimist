package com.stackroute.chatservice.handler;

import com.stackroute.chatservice.model.UserStorage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
    public class SocketHandler extends TextWebSocketHandler {
        List<UserStorage> sessions = new CopyOnWriteArrayList<>();
        @Override
        public void handleTextMessage(WebSocketSession session, TextMessage message)
                throws InterruptedException, IOException
        {
            System.out.println("message came is " + message);
            System.out.println("session cam is" + session);
//        session.sendMessage(message);
//        Gson gson = new Gson();
//        JSONObject p = gson.fromJson(message.getPayload(), JSONObject.class);
//        UserSession userSession=new UserSession();
//        session.sendMessage(message);
//        if (p.get("event").equals("LOGIN")) {
//            userSession.setEvent(p.get("event").toString());
//            userSession.setWebSocketSession(session);
//            userSession.setUsername(p.get("data").toString());
//
//            sessions.add(userSession);
//            System.out.println("inside login");
//            System.out.println(sessions.size());
//        } else {
//            for (UserSession webSocketSession : sessions) {
////            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
////                webSocketSession.sendMessage(message);
////            }
////            webSocketSession.sendMessage(message);
//                System.out.println(p.get("remoteuser"));
//                System.out.println(webSocketSession);
//
//                if (webSocketSession.getUsername().equals(p.get("remoteuser"))) {
//                    webSocketSession.getWebSocketSession().sendMessage(message);
//                }
//            }
//        }
        }
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            System.out.println("sesiion came is :: " + session);
//        sessions.add(session);
            System.out.println(sessions.size());
        }
        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
            //            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
            //                webSocketSession.sendMessage(message);
            //            }
            //            webSocketSession.sendMessage(message);
//        sessions.removeIf(webSocketSession -> webSocketSession.getWebSocketSession().equals(session));
//        sessions.remove(sessions.stream().findFirst().get().getWebSocketSession().equals(session));
        }
    }

