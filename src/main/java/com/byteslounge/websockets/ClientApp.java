package com.byteslounge.websockets;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class ClientApp {

	Session session;
	 private String url =
	 "ws://localhost:8080/com-byteslounge-websockets/xxx";


	public void start() throws IOException {
		WebSocketContainer wc = ContainerProvider.getWebSocketContainer();
		try {
			session = wc.connectToServer(Client.class, URI.create(url));
			// session = wc.connectToServer(Client.class, URI.create(url));
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(System.in)); String input = ""; try { do { input =
		 * br.readLine(); } while (!input.equals("exit")); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */

	}

}
