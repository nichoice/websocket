package com.byteslounge.websockets;


import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;




@ClientEndpoint
public class Client {
	
	private Session session; 
	
	public static ConcurrentHashMap<String,Client> clientWebServerObj = new ConcurrentHashMap<String,Client> ();
	
	@OnOpen
	public void Open(Session session){
		System.out.println("client open:"+session.toString());
		this.setSession(session);
		clientWebServerObj.put("uav0001", this);//网页客户端标识，用户需区分开
		/*try {
			WebSocketTest.sendMessage("xxxxxxxxxxxxxx", WebSocketTest.clientWebServerObj.get("uav0001"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	@OnMessage
	public void Msg(Session session,String message) throws IOException{
		System.out.println("client msg:"+session.toString());
		WebSocketTest.sendMessage(message, WebSocketTest.serverWebServerObj.get("uav0001"));
	}
	
	@OnClose
	public void Close()throws Throwable{
		
	}
	
	@OnError
	public void Error(Throwable t){
		t.printStackTrace();
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}
	
	public static void sendMessage(String message,Client client) throws IOException{
		client.getSession().getAsyncRemote().sendText(message);
    }
	
	
//    public void sendmsg(String message){
//        try{
//            session.getBasicRemote().sendText(message);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
    
}
