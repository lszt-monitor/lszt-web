package com.lm.api.model;

import java.io.Serializable;

/**
 * 数据转换实体类
 *
 * @author Administrator
 */

public class ConversionWindows implements Serializable {
	private String conIp;// 交换机的ip
	private double conBeUsabke_C;// 剩余可用空间内存C盘
	private double conBeUsabke_D;// 剩余可用空间内存D盘
	private double conBeUsabke_E;// 剩余可用空间内存E盘
	private double conBeUsabke_F;// 剩余可用空间内存F盘
	private String conWindowsName;// 机器名称
	private String conWindowsConfig;// 机器的基本信息
	private double conSumRam_C;// C总内存
	private double conSumRam_D;// D总内存
	private double conSumRam_E;// E总内存
	private double conSumRam_F;// F总内存
	private String conWinodwsUpTime;// 上机时间
	private Long conWindowsNumberProcesses;// 进程使用数
	private Long conWindowsPhysicalMemory;// 物理内存使用率
	private Long conWindowsNumberNetworkInterfaces;// 接口数量
	private double conUsed_C;// 已使用C
	private double conUsed_D;// 已使用D
	private double conUsed_E;// 已使用E
	private double conUsed_F;// 已使用F
	private boolean conWindowsFlg;// 判断是否连接

	public ConversionWindows() {

	}

	public String getConIp() {
		return conIp;
	}

	public void setConIp(String conIp) {
		this.conIp = conIp;
	}

	public double getConBeUsabke_C() {
		return conBeUsabke_C;
	}

	public void setConBeUsabke_C(double conBeUsabke_C) {
		this.conBeUsabke_C = conBeUsabke_C;
	}

	public double getConBeUsabke_D() {
		return conBeUsabke_D;
	}

	public void setConBeUsabke_D(double conBeUsabke_D) {
		this.conBeUsabke_D = conBeUsabke_D;
	}

	public double getConBeUsabke_E() {
		return conBeUsabke_E;
	}

	public void setConBeUsabke_E(double conBeUsabke_E) {
		this.conBeUsabke_E = conBeUsabke_E;
	}

	public double getConBeUsabke_F() {
		return conBeUsabke_F;
	}

	public void setConBeUsabke_F(double conBeUsabke_F) {
		this.conBeUsabke_F = conBeUsabke_F;
	}

	public String getConWindowsName() {
		return conWindowsName;
	}

	public void setConWindowsName(String conWindowsName) {
		this.conWindowsName = conWindowsName;
	}

	public String getConWindowsConfig() {
		return conWindowsConfig;
	}

	public void setConWindowsConfig(String conWindowsConfig) {
		this.conWindowsConfig = conWindowsConfig;
	}

	public double getConSumRam_C() {
		return conSumRam_C;
	}

	public void setConSumRam_C(double conSumRam_C) {
		this.conSumRam_C = conSumRam_C;
	}

	public double getConSumRam_D() {
		return conSumRam_D;
	}

	public void setConSumRam_D(double conSumRam_D) {
		this.conSumRam_D = conSumRam_D;
	}

	public double getConSumRam_E() {
		return conSumRam_E;
	}

	public void setConSumRam_E(double conSumRam_E) {
		this.conSumRam_E = conSumRam_E;
	}

	public double getConSumRam_F() {
		return conSumRam_F;
	}

	public void setConSumRam_F(double conSumRam_F) {
		this.conSumRam_F = conSumRam_F;
	}

	public String getConWinodwsUpTime() {
		return conWinodwsUpTime;
	}

	public void setConWinodwsUpTime(String conWinodwsUpTime) {
		this.conWinodwsUpTime = conWinodwsUpTime;
	}

	public Long getConWindowsNumberProcesses() {
		return conWindowsNumberProcesses;
	}

	public void setConWindowsNumberProcesses(Long conWindowsNumberProcesses) {
		this.conWindowsNumberProcesses = conWindowsNumberProcesses;
	}

	public Long getConWindowsPhysicalMemory() {
		return conWindowsPhysicalMemory;
	}

	public void setConWindowsPhysicalMemory(Long conWindowsPhysicalMemory) {
		this.conWindowsPhysicalMemory = conWindowsPhysicalMemory;
	}

	public Long getConWindowsNumberNetworkInterfaces() {
		return conWindowsNumberNetworkInterfaces;
	}

	public void setConWindowsNumberNetworkInterfaces(Long conWindowsNumberNetworkInterfaces) {
		this.conWindowsNumberNetworkInterfaces = conWindowsNumberNetworkInterfaces;
	}

	public double getConUsed_C() {
		return conUsed_C;
	}

	public void setConUsed_C(double conUsed_C) {
		this.conUsed_C = conUsed_C;
	}

	public double getConUsed_D() {
		return conUsed_D;
	}

	public void setConUsed_D(double conUsed_D) {
		this.conUsed_D = conUsed_D;
	}

	public double getConUsed_E() {
		return conUsed_E;
	}

	public void setConUsed_E(double conUsed_E) {
		this.conUsed_E = conUsed_E;
	}

	public double getConUsed_F() {
		return conUsed_F;
	}

	public void setConUsed_F(double conUsed_F) {
		this.conUsed_F = conUsed_F;
	}

	public boolean isConWindowsFlg() {
		return conWindowsFlg;
	}

	public void setConWindowsFlg(boolean conWindowsFlg) {
		this.conWindowsFlg = conWindowsFlg;
	}
}
