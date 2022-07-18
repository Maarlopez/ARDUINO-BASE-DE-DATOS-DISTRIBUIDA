package com.prismamp.node.db.response;

import javax.ws.rs.core.Response;

public interface IIsResponse {

	abstract Response Build();
	public int Code();
	public int CodeHttp();
}
