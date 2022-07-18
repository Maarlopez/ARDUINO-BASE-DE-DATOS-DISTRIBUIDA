package com.prismamp.node.db;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api/v1/db_service")
public class JerseyConfigCustom extends ResourceConfig {

	public JerseyConfigCustom() {

		register(com.prismamp.node.db.controller.AppController.class);
		property(org.glassfish.jersey.server.ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		
	}
}