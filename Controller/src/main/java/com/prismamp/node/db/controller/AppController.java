package com.prismamp.node.db.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.prismamp.node.db.request.GetDataRequest;
import com.prismamp.node.db.response.DataResponse;
import com.prismamp.node.db.response.InsertDataResponse;
import com.prismamp.node.db.response.ManagementResponse;
import com.prismamp.node.db.service.DataService;

@Path("/data")
public class AppController {

	@Autowired
	DataService data_service;

	ManagementResponse mr = new ManagementResponse();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@Context HttpHeaders headers) {
		try {
			return mr.Build(new DataResponse(data_service.getAll()));
		} catch (Exception e) {
			e.printStackTrace();
			return mr.Build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertData(@Context HttpHeaders headers, @RequestBody GetDataRequest data) {
		try {

			String id = data_service.insert(data.getInsert());

			return mr.Build(new InsertDataResponse(id));
		} catch (Exception e) {
			e.printStackTrace();
			return mr.Build();
		}
	}
	
	@POST
	@Path("/internal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertInternalData(@Context HttpHeaders headers, @RequestBody GetDataRequest data) {
		try {

			String id = data_service.internalInsert(data.getInsert());

			return mr.Build(new InsertDataResponse(id));
		} catch (Exception e) {
			e.printStackTrace();
			return mr.Build();
		}
	}
}
