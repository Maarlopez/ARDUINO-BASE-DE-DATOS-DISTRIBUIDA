package com.prismamp.node.db.model;

public class Action {

	EEvent event;
	String extra;

	public Action(EEvent event) {
		this.event = event;
	}

	public Action(EEvent event, String extra) {
		this.event = event;
		this.extra = extra;
	}

	public String getCommand() {
		switch (event) {
		case WRITE:
			String value = event.getCmd() + this.extra;
			return value;
		default:
			return event.getCmd();
		}
	}

	public EEvent getEvent() {
		return event;
	}

	public void setEvent(EEvent event) {
		this.event = event;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
}
