package com.prismamp.node.db.response.exception;

import javax.ws.rs.core.Response;

import com.prismamp.node.db.model.ErrorCode;
import com.prismamp.node.db.response.IIsResponse;
import com.prismamp.node.db.response.IResponse;

public class ExceptionResponse extends IResponse implements IIsResponse {

	protected int http_code = 0;
	protected String description = null;
	protected int code = 0;

	public ExceptionResponse(int code, String description) {
		this.http_code = code;
		this.description = description;
	}

	public ExceptionResponse(int http_code, int code_error, String description) {
		this.http_code = http_code;
		this.code = code_error;
		this.description = description;
	}

	public ExceptionResponse(int http_code, ErrorCode errorCode) {
		this.http_code = http_code;
		this.code = errorCode.getCodeError();
		this.description = errorCode.getDescription();
	}

	public ExceptionResponse(int http_code, ErrorCode errorCode, String descpiption) {
		this.http_code = http_code;
		this.code = errorCode.getCodeError();
		this.description = errorCode.getDescription() + " " + descpiption;
	}

	public ExceptionResponse(int http_code, int code, ErrorCode errorCode, String descpiption) {
		this.http_code = http_code;
		this.code = code;
		this.description = errorCode.getDescription() + " " + descpiption;
	}

	public ExceptionResponse(ErrorCode errorCode, String descpiption) {
		this.http_code = errorCode.getCodeError();
		this.code = errorCode.getCodeError();
		this.description = errorCode.getDescription() + " " + descpiption;
	}

	@Override
	public Response Build() {
		return super.BuildResponse(this.http_code, this);
	}

	public int getHttp_code() {
		return http_code;
	}

	public void setHttp_code(int http_code) {
		this.http_code = http_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public int Code() {
		return this.code;
	}

	@Override
	public int CodeHttp() {
		return this.http_code;
	}

}
