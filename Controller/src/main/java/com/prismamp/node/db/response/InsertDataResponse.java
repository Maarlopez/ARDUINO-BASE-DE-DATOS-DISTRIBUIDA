package com.prismamp.node.db.response;

import javax.ws.rs.core.Response;

public class InsertDataResponse extends GenericResponse implements IIsResponse {

	String id;

	public InsertDataResponse(String id) {
		super(200);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Response Build() {
		return super.BuildResponse(super.code, this);
	}

}
