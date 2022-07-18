package com.prismamp.node.db.service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.prismamp.node.db.model.Action;
import com.prismamp.node.db.model.EEvent;
import com.prismamp.node.db.service.component.Runner;
import com.prismamp.node.db.service.component.SocketController;

import org.springframework.core.annotation.Order;

@Service
@Order(1)
public class ArdService {

	private SocketController socket;
	private ConcurrentLinkedQueue<Action> actions = new ConcurrentLinkedQueue<Action>();
	private Runner runner = new Runner(this);

	public static String HOST;
	public static int PORT;

	public ArdService() {
		this.socket = new SocketController(HOST, PORT);
		this.runner.start();
	}

	public void add(Action action) {
		if (action.getEvent() == EEvent.WRITE)
			this.actions.add(action);
	}

	public Action getAction() {
		return this.actions.poll();
	}

	public HashMap<String, String> send(Action action) {
		String response = this.socket.send(action.getCommand());
		return process(action, response);
	}

	private HashMap<String, String> process(Action action, String response) {
		HashMap<String, String> resp = new HashMap<String, String>();
		switch (action.getEvent()) {
		case WRITE:
			String id = response.split("([0-9])")[0];
			resp.put("id", id);
			break;		
		case READ_ALL:
			String[] rows = response.split("\n");
			for (String value : rows) {
				if (!value.contains("END") && value.replace("\r", "") != "") {
					String[] key_val = value.replace("\r", "").split("/");
					if (key_val.length > 1 && key_val.length <= 2)
						resp.put(key_val[0], key_val[1]);
					else if (key_val.length > 2) {
						String val = "";
						for (int x = 1; x < key_val.length; x++) {
							val += "/" + key_val[x];
						}
						resp.put(key_val[0], val.substring(1, val.length() - 1));
					} else
						resp.put(key_val[0], "");
				}
			}
			break;
		}
		return resp;
	}

}
