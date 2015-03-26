package com.liyang.example.nio;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class EchoHandler implements Runnable{
	
	private SocketChannel socketChannel;
	
	public EchoHandler(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	public void run() {
		try {
			System.out.println("处理请求");
			System.out.println(socketChannel.getRemoteAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
