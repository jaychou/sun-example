package com.liyang.example.netty.plain;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		System.out.println("接受请求");
	}
	
	

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		
		System.out.println("客户端连接成功" + ctx.channel().remoteAddress());
	}



	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelReadComplete(ctx);
	}
	
	
	
}
