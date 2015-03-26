package com.liyang.example.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTest {
	
	public static void main(String args[]) throws IOException {
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(8000));
		
		OutputStream output = socket.getOutputStream();
		//output.write(new String("jaychou").getBytes());
		//output.flush();
		
		socket.close();
	}

}
