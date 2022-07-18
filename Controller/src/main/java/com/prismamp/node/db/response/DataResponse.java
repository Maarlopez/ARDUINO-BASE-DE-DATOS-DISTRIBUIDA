package com.prismamp.node.db.response;

import java.util.HashMap;

import javax.ws.rs.core.Response;

public class DataResponse extends GenericResponse implements IIsResponse {

	HashMap<String, String> data;

	public DataResponse(HashMap<String, String> data) {
		super(200);
		this.data = data;
	}

	@Override
	public Response Build() {
		return super.BuildResponse(super.code, this);
	}

	public HashMap<String, String> getData() {
		return data;
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

}
