package com.prismamp.node.db.response.exception;

import com.prismamp.node.db.model.ErrorCode;

public class ExceptionBadRequest extends ExceptionResponse {

	static int http_code = 400;

	public ExceptionBadRequest() {
		super(http_code, ErrorCode.BAD_REQUEST);
	}

	public ExceptionBadRequest(ErrorCode errorCode) {
		super(http_code, errorCode);
	}

	public ExceptionBadRequest(ErrorCode errorCode, String description) {
		super(http_code, errorCode, description);
	}

	public ExceptionBadRequest(String description) {
		super(http_code, ErrorCode.BAD_REQUEST, description);
	}

}
