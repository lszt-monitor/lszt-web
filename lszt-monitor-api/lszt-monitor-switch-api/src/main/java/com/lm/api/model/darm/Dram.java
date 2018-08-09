package com.lm.api.model.darm;

import java.io.Serializable;
import java.util.Date;
public class Dram  implements Serializable {
	// 物理内存id
	private Integer idSwitchDram;
	private Long id;
	// 物理内存百分比
	private Integer utilDram;
	// 物理内存总
	private Long totalDram;
	// 物理内存已用
	private Long usedDram;

	private Date time;

	public Dram() {

	}

	public Integer getIdSwitchDram() {
		return idSwitchDram;
	}

	public void setIdSwitchDram(Integer idSwitchDram) {
		this.idSwitchDram = idSwitchDram;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUtilDram() {
		return utilDram;
	}

	public void setUtilDram(Integer utilDram) {
		this.utilDram = utilDram;
	}

	public Long getTotalDram() {
		return totalDram;
	}

	public void setTotalDram(Long totalDram) {
		this.totalDram = totalDram;
	}

	public Long getUsedDram() {
		return usedDram;
	}

	public void setUsedDram(Long usedDram) {
		this.usedDram = usedDram;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Dram [idSwitchDram=" + idSwitchDram + ", id=" + id + ", utilDram=" + utilDram + ", totalDram="
				+ totalDram + ", usedDram=" + usedDram + ", time=" + time + "]";
	}

	public Dram(Integer idSwitchDram, Integer utilDram, Long totalDram, Long usedDram, Date time) {
		super();
		this.idSwitchDram = idSwitchDram;
		this.utilDram = utilDram;
		this.totalDram = totalDram;
		this.usedDram = usedDram;
		this.time = time;
	}

}
