package com.prismamp.node.db.service.component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketController {

	private String ip;
	private int port;

	public SocketController(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public String send(String cmd) {
		Socket socket = null;

		try {
			socket = new Socket(this.ip, this.port);
			socket.setSoTimeout(10000);

			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();

			outputStream.write(cmd.getBytes("UTF-8"));
			outputStream.flush();

			byte[] response = inputStream.readAllBytes();

			return new String(response);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {

				}
		}

	}

}
