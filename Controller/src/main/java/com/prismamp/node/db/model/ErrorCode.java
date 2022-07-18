package com.prismamp.node.db.model;

public enum ErrorCode {
	INTERNAL_SERVER_ERROR_GENERAL("5000-Internal server error."),
	INTERNAL_SERVER_ERROR_PARSE_JSON("5001-Could not parse the json for the request."),	
	FORBIDDEN("4003-Service Forbidden."), 
	BAD_REQUEST("4000-Bad Request."),
	BAD_REQUEST_USER("4001-Invalid User."),
	BAD_REQUEST_RETRY_SMS("4002-Wait for retry send SMS."),
	BAD_REQUEST_SEND_SMS("4003-Cannot send SMS."),
	BAD_REQUEST_INVALID_VERIFY("4004-Invalid data for verify."),
	;

	private int codeError;
	private String description;

	ErrorCode(String codigo) {
		String[] parse = codigo.split("-");
		this.codeError = Integer.parseInt(parse[0]);
		this.description = parse[1];
	}

	public int getCodeError() {
		return codeError;
	}

	public String getDescription() {
		return description;
	}

}
