package com.lm.api.model.cpu;

import java.io.Serializable;
import java.util.Date;
public class Cpu  implements Serializable {
	// 物理内存id
	private Integer idSwitchCpu;
	private Long id;
	// 物理内存百分比
	private Integer cpuSec;
	// 物理内存总
	private Integer cpu1Min;
	// 物理内存已用
	private Integer cpu5Min;
	// 存入时间
	private Date time;

	public Cpu() {
	}

	public Integer getIdSwitchCpu() {
		return idSwitchCpu;
	}

	public void setIdSwitchCpu(Integer idSwitchCpu) {
		this.idSwitchCpu = idSwitchCpu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCpuSec() {
		return cpuSec;
	}

	public void setCpuSec(Integer cpuSec) {
		this.cpuSec = cpuSec;
	}

	public Integer getCpu1Min() {
		return cpu1Min;
	}

	public void setCpu1Min(Integer cpu1Min) {
		this.cpu1Min = cpu1Min;
	}

	public Integer getCpu5Min() {
		return cpu5Min;
	}

	public void setCpu5Min(Integer cpu5Min) {
		this.cpu5Min = cpu5Min;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Cpu{" +
				"idSwitchCpu=" + idSwitchCpu +
				", id=" + id +
				", cpuSec=" + cpuSec +
				", cpu1Min=" + cpu1Min +
				", cpu5Min=" + cpu5Min +
				", time=" + time +
				'}';
	}

	public Cpu(Integer idSwitchCpu, Integer cpuSec, Integer cpu1Min, Integer cpu5Min, Date time) {
		this.idSwitchCpu = idSwitchCpu;
		this.cpuSec = cpuSec;
		this.cpu1Min = cpu1Min;
		this.cpu5Min = cpu5Min;
		this.time = time;
	}
}
