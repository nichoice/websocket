package com.byteslounge.websockets;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/xxx")
public class WebSocketTest2 {
	
	
	@OnMessage
    public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		
		System.out.println("无人机收到消息："+message);
    }
	
	@OnOpen
	public void onOpen(Session session) throws IOException {
		Thread thread = new Thread(new MyThread(session));
		thread.start();
	}

    @OnClose
    public void onClose () throws Throwable {
    	System.out.println("Connection closed");
    }
	 
	 
}
