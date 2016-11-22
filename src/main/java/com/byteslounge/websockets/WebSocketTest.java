package com.byteslounge.websockets;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/websocket")
public class WebSocketTest {
	
	private Session session; 
	
	public static ConcurrentHashMap<String,WebSocketTest> serverWebServerObj = new ConcurrentHashMap<String,WebSocketTest> ();
	
	@OnMessage
    public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		System.out.println("onMessage session"+session.toString());
		// Print the client message for testing purposes
		System.out.println("Received: " + message);
		Client.sendMessage(message, Client.clientWebServerObj.get("uav0001"));
		session.getBasicRemote().sendText(message);
		// Send the first message to the client
		session.getBasicRemote().sendText("This is the first server message");
		
		// Send 3 messages to the client every 5 seconds
		int sentMessages = 0;
		while(sentMessages < 3){
			Thread.sleep(5000);
			session.getBasicRemote().
				sendText("This is an intermediate server message. Count: " 
					+ sentMessages);
			sentMessages++;
		}
		
		// Send a final message to the client
		session.getBasicRemote().sendText("This is the last server message");
    }
	
	@OnOpen
	public void onOpen(Session session) throws IOException {
		this.setSession(session);
		serverWebServerObj.put("uav0001", this);//网页客户端标识，用户需区分开
		System.out.println("onOpen session"+session.toString());
		ClientApp ca = new ClientApp();
		ca.start();
		System.out.println("onOpen session11"+session.toString());
	}

    @OnClose
    public void onClose () throws Throwable {
    	System.out.println("Connection closed");
    }

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
    
	public static void sendMessage(String message,WebSocketTest webServer) throws IOException{
		webServer.getSession().getBasicRemote().sendText(message);
    }
}
