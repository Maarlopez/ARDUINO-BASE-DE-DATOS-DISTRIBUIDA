package com.prismamp.node.db.response;

import javax.ws.rs.core.Response;

public class IResponse {

	public Response BuildResponse(int code, IIsResponse resp) {
		return Response.status(code).entity(resp).build();
	}
}
