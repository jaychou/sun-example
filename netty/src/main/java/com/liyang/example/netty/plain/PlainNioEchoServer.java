package com.liyang.example.netty.plain;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class PlainNioEchoServer {
	
	public void service(int port) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress(port));
		serverSocketChannel.configureBlocking(false);
		System.out.println("服务器启动："+port);
		
		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		
		while(true) {
			
			try {
				selector.select();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()) {
				
				SelectionKey key = iterator.next();
				iterator.remove();
				
				if(key.isAcceptable()) {
				    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
				    SocketChannel socket = channel.accept();
				    socket.configureBlocking(false);
					System.out.println("请受请求");
					socket.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE, ByteBuffer.allocate(100));
					
				}
				
				if(key.isReadable()) {
					SocketChannel socket = (SocketChannel)key.channel();
					ByteBuffer buffer = (ByteBuffer)key.attachment();
					socket.read(buffer);
				}
				
				if(key.isWritable()) {
					SocketChannel socket = (SocketChannel)key.channel();
					ByteBuffer buffer = (ByteBuffer)key.attachment();
					buffer.flip();
					socket.write(buffer);
					buffer.compact();
					
				}
				
			}
			
			
			
		}
	}
	
	public static void main(String args[]) {
		try {
			new PlainNioEchoServer().service(8000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
