package com.prismamp.node.db.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.prismamp.node.db.model.Action;
import com.prismamp.node.db.model.EEvent;

@Service
@Order(3)
public class DataService {

	NodeService node_service;

	ArdService ard_controller;

	private LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

	public DataService(@Autowired ArdService ard_controller, @Autowired NodeService node_service) {
		this.ard_controller = ard_controller;
		this.node_service = node_service;
		try {
			data.putAll(ard_controller.send(new Action(EEvent.READ_ALL)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String insert(String value) {

		String id = generateUUID();
		String key_data = id + "/" + value;

		data.put(id, value);
		node_service.notify(key_data);
		ard_controller.add(new Action(EEvent.WRITE, key_data));
		return id;
	}

	public String internalInsert(String key_value_node) {

		String[] key_value = key_value_node.split("/");

		String key = key_value[0];
		String value = "";
		if (key_value.length > 2) {
			String val = "";
			for (int x = 1; x < key_value.length; x++) {
				val += "/" + key_value[x];
			}
			value = val.substring(1, val.length() - 1);
		} else
			value = key_value[1];

		data.put(key, value);
		ard_controller.add(new Action(EEvent.WRITE, key_value_node));
		return key;
	}

	public HashMap<String, String> getAll() {
		return data;
	}

	private String generateUUID() {
		return UUID.randomUUID().toString();
	}
}
