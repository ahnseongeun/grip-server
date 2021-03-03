package com.app.grip.src.chatting;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SocketHandler extends TextWebSocketHandler {
    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingProvider chattingProvider;

    // 웹 소켓 세션을 담아둘 리스트(roomListSessions)
    List<HashMap<String, Object>> rls = new ArrayList<>();
    // 채팅 메시지 모음
    List<JSONObject> msgList = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        JSONObject obj = jsonToObjectParser(msg);

        String rN = obj.get("chattingRoomId") + "";
        msgList.add(obj);

        // chattingRoomId 별로 대화내용 노출
        for(JSONObject jsonObject : msgList) {
            if(jsonObject.get("chattingRoomId").toString().equals(rN)) {
                session.sendMessage(new TextMessage(jsonObject.toJSONString()));
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        boolean flag = false;

        String url = session.getUri().toString();
        String chattingRoomId = url.split("/chatting/")[1];

        // 채팅방이 만들어져 있는지 확인
        try {
            chattingRoomRepository.findByStatusAndId("Y", Long.parseLong(chattingRoomId)).get(0);
        } catch (Exception exception) {
            JSONObject obj = new JSONObject();
            obj.put("isSuccess", "false");
            obj.put("code", "3122");
            obj.put("message", "채팅방 검색에 실패하였습니다.");
            session.sendMessage(new TextMessage(obj.toJSONString()));
            session.close();
            return;
        }

        // 만들어진 채팅방의 세션이 존재하는지 확인
        int idx = rls.size();
        if(rls.size() > 0) {
            for(int i = 0; i < rls.size(); i++) {
                String rN = (String) rls.get(i).get("chattingRoomId");
                if(rN.equals(chattingRoomId)) {
                    flag = true;
                    idx = i;
                    break;
                }
            }
        }

        if(flag) {                      // 존재하는 방이라면 세션만 추가한다.
            HashMap<String, Object> map = rls.get(idx);
            map.put(session.getId(), session);
        } else {                        // 최초 생성하는 방이라면 방번호와 세션을 추가한다.
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("chattingRoomId", chattingRoomId);
            map.put(session.getId(), session);
            rls.add(map);
        }

        // 세션등록이 끝나면 발급받은 세션 ID의 메시지 발송
        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId());
        session.sendMessage(new TextMessage(obj.toJSONString()));

        // 기존 대화 내용 있다면 불러오기
        for(JSONObject jsonObject : msgList) {
            if(jsonObject.get("chattingRoomId").toString().equals(chattingRoomId)) {
                session.sendMessage(new TextMessage(jsonObject.toJSONString()));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료
        if(rls.size() > 0) {            //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
            for(int i = 0; i < rls.size(); i++) {
                rls.get(i).remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;

        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}