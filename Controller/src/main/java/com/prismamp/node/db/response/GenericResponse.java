package com.prismamp.node.db.response;

import javax.ws.rs.core.Response;

public class GenericResponse extends IResponse {

	protected int code = 0;

	public GenericResponse(int code) {
		this.code = code;
	}

	public Response Build(IIsResponse resp) {
		return super.BuildResponse(this.code, resp);
	}

	public int Code() {
		return -1;
	}

	public int CodeHttp() {
		return this.code;
	}

}
