package server;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -1724444053149327065L;
	private String name;
	private String msg;

	public Message(String name, String msg) {
		this.setName(name);
		this.setMsg(msg);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
