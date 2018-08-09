package com.lm.api.model.windows;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class PetWindowsPc {
	private String ip;// 交换机的ip
	private Integer wId;// 判断是哪台主机
	private double beUsabkeC;// 剩余可用空间内存C盘
	private double beUsabkeD;// 剩余可用空间内存D盘
	private double beUsabkeE;// 剩余可用空间内存E盘
	private double beUsabkeF;// 剩余可用空间内存F盘
	private String windowsName;// 机器名称
	private String windowsConfig;// 机器的基本信息
	private double sumRamC;// C总内存
	private double sumRamD;// D总内存
	private double sumRamE;// E总内存
	private double sumRamF;// F总内存
	private Date winodwsUpTime;// 上机时间
	private Long windowsNumberProcesses;// 进程使用数
	private Long windowsPhysicalMemory;// 物理内存使用率
	private Long windowsNumberNetworkInterfaces;// 接口数量
	private double usedC;// 已使用C
	private double usedD;// 已使用D
	private double usedE;// 已使用E
	private double usedF;// 已使用F
	// 1代表连接 0代表没连接
	private int windowsFlg;// 判断是否连接

	public PetWindowsPc() {

	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getwId() {
		return wId;
	}

	public void setwId(Integer wId) {
		this.wId = wId;
	}

	public double getBeUsabkeC() {
		return beUsabkeC;
	}

	public void setBeUsabkeC(double beUsabkeC) {
		this.beUsabkeC = beUsabkeC;
	}

	public double getBeUsabkeD() {
		return beUsabkeD;
	}

	public void setBeUsabkeD(double beUsabkeD) {
		this.beUsabkeD = beUsabkeD;
	}

	public double getBeUsabkeE() {
		return beUsabkeE;
	}

	public void setBeUsabkeE(double beUsabkeE) {
		this.beUsabkeE = beUsabkeE;
	}

	public double getBeUsabkeF() {
		return beUsabkeF;
	}

	public void setBeUsabkeF(double beUsabkeF) {
		this.beUsabkeF = beUsabkeF;
	}

	public String getWindowsName() {
		return windowsName;
	}

	public void setWindowsName(String windowsName) {
		this.windowsName = windowsName;
	}

	public String getWindowsConfig() {
		return windowsConfig;
	}

	public void setWindowsConfig(String windowsConfig) {
		this.windowsConfig = windowsConfig;
	}

	public double getSumRamC() {
		return sumRamC;
	}

	public void setSumRamC(double sumRamC) {
		this.sumRamC = sumRamC;
	}

	public double getSumRamD() {
		return sumRamD;
	}

	public void setSumRamD(double sumRamD) {
		this.sumRamD = sumRamD;
	}

	public double getSumRamE() {
		return sumRamE;
	}

	public void setSumRamE(double sumRamE) {
		this.sumRamE = sumRamE;
	}

	public double getSumRamF() {
		return sumRamF;
	}

	public void setSumRamF(double sumRamF) {
		this.sumRamF = sumRamF;
	}

	public Date getWinodwsUpTime() {
		return winodwsUpTime;
	}

	public void setWinodwsUpTime(Date winodwsUpTime) {
		this.winodwsUpTime = winodwsUpTime;
	}

	public Long getWindowsNumberProcesses() {
		return windowsNumberProcesses;
	}

	public void setWindowsNumberProcesses(Long windowsNumberProcesses) {
		this.windowsNumberProcesses = windowsNumberProcesses;
	}

	public Long getWindowsPhysicalMemory() {
		return windowsPhysicalMemory;
	}

	public void setWindowsPhysicalMemory(Long windowsPhysicalMemory) {
		this.windowsPhysicalMemory = windowsPhysicalMemory;
	}

	public Long getWindowsNumberNetworkInterfaces() {
		return windowsNumberNetworkInterfaces;
	}

	public void setWindowsNumberNetworkInterfaces(Long windowsNumberNetworkInterfaces) {
		this.windowsNumberNetworkInterfaces = windowsNumberNetworkInterfaces;
	}

	public double getUsedC() {
		return usedC;
	}

	public void setUsedC(double usedC) {
		this.usedC = usedC;
	}

	public double getUsedD() {
		return usedD;
	}

	public void setUsedD(double usedD) {
		this.usedD = usedD;
	}

	public double getUsedE() {
		return usedE;
	}

	public void setUsedE(double usedE) {
		this.usedE = usedE;
	}

	public double getUsedF() {
		return usedF;
	}

	public void setUsedF(double usedF) {
		this.usedF = usedF;
	}

	public int getWindowsFlg() {
		return windowsFlg;
	}

	public void setWindowsFlg(int windowsFlg) {
		this.windowsFlg = windowsFlg;
	}

	@Override
	public String toString() {
		return "PetWindowsPc [ip=" + ip + ", wId=" + wId + ", beUsabkeC=" + beUsabkeC + ", beUsabkeD=" + beUsabkeD
				+ ", beUsabkeE=" + beUsabkeE + ", beUsabkeF=" + beUsabkeF + ", windowsName=" + windowsName
				+ ", windowsConfig=" + windowsConfig + ", sumRamC=" + sumRamC + ", sumRamD=" + sumRamD + ", sumRamE="
				+ sumRamE + ", sumRamF=" + sumRamF + ", winodwsUpTime=" + winodwsUpTime + ", windowsNumberProcesses="
				+ windowsNumberProcesses + ", windowsPhysicalMemory=" + windowsPhysicalMemory
				+ ", windowsNumberNetworkInterfaces=" + windowsNumberNetworkInterfaces + ", usedC=" + usedC + ", usedD="
				+ usedD + ", usedE=" + usedE + ", usedF=" + usedF + ", windowsFlg=" + windowsFlg + "]";
	}

	public PetWindowsPc(String ip, Integer wId, double beUsabkeC, double beUsabkeD, double beUsabkeE, double beUsabkeF,
			String windowsName, String windowsConfig, double sumRamC, double sumRamD, double sumRamE, double sumRamF,
			Date winodwsUpTime, Long windowsNumberProcesses, Long windowsPhysicalMemory,
			Long windowsNumberNetworkInterfaces, double usedC, double usedD, double usedE, double usedF,
			int windowsFlg) {
		super();
		this.ip = ip;
		this.wId = wId;
		this.beUsabkeC = beUsabkeC;
		this.beUsabkeD = beUsabkeD;
		this.beUsabkeE = beUsabkeE;
		this.beUsabkeF = beUsabkeF;
		this.windowsName = windowsName;
		this.windowsConfig = windowsConfig;
		this.sumRamC = sumRamC;
		this.sumRamD = sumRamD;
		this.sumRamE = sumRamE;
		this.sumRamF = sumRamF;
		this.winodwsUpTime = winodwsUpTime;
		this.windowsNumberProcesses = windowsNumberProcesses;
		this.windowsPhysicalMemory = windowsPhysicalMemory;
		this.windowsNumberNetworkInterfaces = windowsNumberNetworkInterfaces;
		this.usedC = usedC;
		this.usedD = usedD;
		this.usedE = usedE;
		this.usedF = usedF;
		this.windowsFlg = windowsFlg;
	}

}
