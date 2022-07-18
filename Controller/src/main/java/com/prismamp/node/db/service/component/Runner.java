package com.prismamp.node.db.service.component;

import java.util.concurrent.TimeUnit;

import com.prismamp.node.db.model.Action;
import com.prismamp.node.db.service.ArdService;

public class Runner extends Thread {

	private ArdService controller;

	public Runner(ArdService controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		while(true) {
			Action action = this.controller.getAction();
			if(action!=null) {
				this.controller.send(action);
			}else {
				try {
					TimeUnit.MICROSECONDS.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
