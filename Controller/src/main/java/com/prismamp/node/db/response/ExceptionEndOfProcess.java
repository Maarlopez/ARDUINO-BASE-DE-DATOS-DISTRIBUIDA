package com.prismamp.node.db.response;

import com.prismamp.node.db.response.exception.ExceptionResponse;

public class ExceptionEndOfProcess extends Exception {

	private static final long serialVersionUID = 1L;

	private ExceptionResponse exception = null;

	public ExceptionEndOfProcess() {

	}

	public ExceptionEndOfProcess(String info) {
		super(info);
	}

	public ExceptionEndOfProcess(ExceptionResponse exception) {
		this.exception = exception;
	}

	public ExceptionResponse getException() {
		return this.exception;
	}

}
