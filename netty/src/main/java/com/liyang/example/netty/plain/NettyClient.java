package com.liyang.example.netty.plain;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	
	public static void main(String args[]) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			
			Bootstrap client = new Bootstrap();
			client.group(workerGroup);
			client.channel(NioSocketChannel.class);
			client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                   ch.pipeline().addLast(new NettyClientHandler());
                }
            });
			
			ChannelFuture f = client.connect("127.0.0.1", 8000).sync();
			f.channel().closeFuture().sync();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
