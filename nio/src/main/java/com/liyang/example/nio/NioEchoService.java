package com.liyang.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioEchoService {
	private ServerSocketChannel serverSocketChannel = null;
	private Selector selector = null;
	private static int PORT = 8000;
	
	public NioEchoService() throws IOException {
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
		serverSocketChannel.configureBlocking(false);
		selector = Selector.open();
		
	}
	
	
	public void service() throws IOException {
		
		System.out.println("服务器启动");
		
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		
			while(selector.select()>0) {
				
				System.out.println(selector.select());
				
				
				System.out.println("接受请求");
				Iterator<SelectionKey> iterator = selector.keys().iterator();
				while(iterator.hasNext()) {
					SelectionKey key = iterator.next();
					
					if(key.isAcceptable()) {
						System.out.println("可接受的请求");
						ServerSocketChannel server = (ServerSocketChannel)key.channel();
						SocketChannel socket = server.accept();
						socket.configureBlocking(false);
						socket.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
						
					}
					
					if(key.isConnectable()) {
						System.out.println("连接请求");
					}
					
					if(key.isReadable()) {
						System.out.println("读请求");
					}
					
					if(key.isWritable()) {
						System.out.println("写请求");
					}
				}
			}
		
	}

	
	public static void main(String args[]) throws Exception {
		new NioEchoService().service();
	}
}
