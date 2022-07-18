package com.prismamp.node.db.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.prismamp.node.db.model.Node;
import com.prismamp.node.db.service.component.NotifyRunner;

@Service
@Order(2)
public class NodeService {

	List<Node> nodes = new ArrayList<Node>();

	public static String NODES = "";

	public NodeService() {
		String[] str_nodes = NODES.split(",");
		if (str_nodes.length > 0 && str_nodes.length >= 1)
			for (String node : str_nodes) {
				if (node.indexOf(":") > 0) {
					String[] str_node = node.split(":");
					if (str_node.length == 2)
						this.nodes.add(new Node(str_node[0], Integer.valueOf(str_node[1])));
				}
			}
	}

	public void notify(String value) {
		NotifyRunner notify = new NotifyRunner(this.nodes, value);
		notify.start();
	}

}
