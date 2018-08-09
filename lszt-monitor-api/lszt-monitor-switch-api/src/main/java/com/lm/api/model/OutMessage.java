package com.lm.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * webSocket
 */
public class  OutMessage  implements Serializable {
	/**
	 * 从哪里来
	 */
	private String from;
	/**
	 * 输出的内容
	 */
	private  Object content;
	/**'
	 * 时间
	 */
	private Date time = new Date();

	public OutMessage() {
	};

	public OutMessage(Object content){
		this.content = content;
	};

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
