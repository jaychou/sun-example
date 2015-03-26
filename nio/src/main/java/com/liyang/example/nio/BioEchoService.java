package com.liyang.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioEchoService {
	
	private static int PORT = 8000;
	private ServerSocketChannel serverSocketChannel = null;
	private ExecutorService executorService = null;
	private static int POOL_INT = 4;
	
	public BioEchoService() throws IOException {
		serverSocketChannel = ServerSocketChannel.open();
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_INT);
		serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
		System.out.println("服务器启动");
		
	}
	
	public void service() {
		SocketChannel socketChannel = null;
		while(true) {
			try {
				socketChannel = serverSocketChannel.accept();
				executorService.execute(new EchoHandler(socketChannel));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		new BioEchoService().service();
	}

}
