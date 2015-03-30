package com.liyang.example.netty.plain;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyEchoServer {
	private int port;
	
	public NettyEchoServer(int port) {
		this.port = port;
	}
	
	public void start() {
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		
		try {
			bootstrap.group(bossGroup, workGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>(){
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoServerHandler());
				}
				
			});
			System.out.println("服务器启动....");
			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();
			System.out.println("服务器阻塞....");
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) {
		new NettyEchoServer(8000).start();
	}

}
