package com.byteslounge.websockets;

import java.io.IOException;

import javax.websocket.Session;

public class MyThread implements Runnable{
	
	private Session  session; //服务端session
	
	 public MyThread( Session session){
		 this.session = session;
	 }
	 
	@Override
	public void run() {
		int sentMessages = 0;
		while(sentMessages < 3){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				session.getBasicRemote().sendText("无人机. Count: " + sentMessages);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sentMessages++;
	}}

}
