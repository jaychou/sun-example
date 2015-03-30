package com.liyang.example.netty.plain;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String args[]) throws IOException {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(8000));
			socket.getOutputStream().write(new String("hello").getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}

}
