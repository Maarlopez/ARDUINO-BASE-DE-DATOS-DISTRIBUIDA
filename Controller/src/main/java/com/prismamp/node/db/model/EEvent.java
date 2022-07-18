package com.prismamp.node.db.model;

public enum EEvent {
	READ_ALL("#R#"), WRITE("#S#");

	public final String cmd;

	EEvent(String cmd) {
		this.cmd = cmd;
	}

	public String getCmd() {
		return this.cmd;
	}

}
