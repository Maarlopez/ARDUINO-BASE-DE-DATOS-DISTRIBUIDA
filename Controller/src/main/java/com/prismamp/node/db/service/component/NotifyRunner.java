package com.prismamp.node.db.service.component;

import java.io.IOException;
import java.util.List;

import com.prismamp.node.db.model.Node;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotifyRunner extends Thread {

	private List<Node> nodes;
	private OkHttpClient client = new OkHttpClient();
	private String value;

	public NotifyRunner(List<Node> nodes, String value) {
		this.nodes = nodes;
		this.value = value;
	}

	@Override
	public void run() {
		for (Node node : nodes) {

			RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
					"{\"insert\":\"" + this.value + "\"}");
			Request request = new Request.Builder()
					.url("http://" + node.getHost() + ":" + node.getPort() + "/api/v1/db_service/data/internal").post(body)
					.addHeader("Content-Type", "application/json").build();

			Response response = null;
			try {
				Call call = client.newCall(request);
				response = call.execute();

				if (response.isSuccessful()) {
					System.out.println("NOTIFICATION SUCCESSFUL...");
				} else {
					System.out.println("NOTIFICATION ERROR!!");
				}

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("NOTIFICATION ERROR!!");
			} finally {
				if (response != null)
					response.close();
			}
		}

	}

}
