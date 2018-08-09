package com.lm.toos;
/**
 * walk跟Bulk请求OID
 * @author Administrator
 *
 */
public enum E_Oid {
	// 传输帧速率OID Walk请求
	TMPORT {public String getValue() {return ".1.3.6.1.4.1.171.12.1.1.8.1.2";}},
	//接收帧速率OID Walk请求
	REPORT{public String getValue() {return ".1.3.6.1.4.1.171.12.1.1.8.1.3";}},
	//目前端口统计的百分比OID Walk请求
	PEPORT{public String getValue() {return ".1.3.6.1.4.1.171.12.1.1.8.1.4";}},
	// CPU使用3种状态 OID Walk请求
	CPU{public String getValue() {return ".1.3.6.1.4.1.171.12.1.1.6";}},
	//windows发现硬盘有多少OID Walk请求
	HRPARTITIONIMDEX{public String getValue() {return ".1.3.6.1.2.1.25.2.3.1.1";}},
	// 获得RAM内存  Walk请求
	DRAMVALUE{public String getValue() {return ".1.3.6.1.4.1.171.12.1.1.9";}},
	// 获得FLAS内存  Walk请求
	FLASVALUE{public String getValue() {return ".1.3.6.1.4.1.171.12.1.1.10";}};

	public abstract String getValue();
}
