package com.prismamp.node.db.response;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

public class ManagementResponse {

	public IIsResponse resp = null;

	public void send(IIsResponse res) throws ExceptionEndOfProcess {
		this.resp = res;
		throw new ExceptionEndOfProcess("EOF");
	}

	public Response Build() {
		if (resp != null) {
			return this.resp.Build();
		} else {
			return null;
		}
	}

	public Response Build(IIsResponse resp) {
		if (resp != null) {
			this.resp = resp;
			return resp.Build();
		} else {
			return this.resp.Build();
		}
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this.resp);
	}
}
