package com.prismamp.node.db;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prismamp.node.db.service.ArdService;
import com.prismamp.node.db.service.NodeService;

@SpringBootApplication
public class DbApplication {

	public static void main(String[] args) {

		ArdService.HOST = System.getenv("HOST");
		ArdService.PORT = Integer.valueOf(System.getenv("PORT"));
		NodeService.NODES = System.getenv("NODES");

		SpringApplication app = new SpringApplication(DbApplication.class);
		Map<String, Object> params = new HashMap<String, Object>();
		String port = System.getenv("APP_PORT");
		params.put("server.port", port);

		app.setDefaultProperties(params);
		app.run(args);
	}

}
