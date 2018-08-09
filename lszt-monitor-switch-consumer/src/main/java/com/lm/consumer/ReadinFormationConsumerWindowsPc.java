package com.lm.consumer;

import com.lm.service.windowsImpl.WrealtimPcMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.lm.api.model.ConversionWindows;

@Component
public class ReadinFormationConsumerWindowsPc {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WrealtimPcMapperImpl wrealtimMapperImpl;

	// @SendTo("out.queue") 如果有返回值 返回到哪里
	// 监听的队列名称的
	// @JmsListener(destination = "192.168.32.146")
	public void getWindows(String json) {
		log.info(json);
		// resolveJson(json);

	}

	private void resolveJson(String json) {
		ConversionWindows cvWd = new ConversionWindows();
		JSONObject rootJson = null;
		// 转换成JSON 类型
		rootJson = JSONObject.parseObject(json);
		// 获得机器ip
		if (rootJson.containsKey("ip")) {

			cvWd.setConIp(rootJson.getString("ip"));
		}
		// 获得机器名字
		if (rootJson.containsKey("windowsName")) {

			cvWd.setConWindowsName(rootJson.getString("windowsName"));
		}
		// 获得上机时间
		if (rootJson.containsKey("windowsTime")) {

			cvWd.setConWinodwsUpTime(rootJson.getString("windowsTime"));
		}
		// 判断是否已连接
		if (rootJson.containsKey("windowsflg")) {
			cvWd.setConWindowsFlg(rootJson.getBoolean("windowsflg"));
		}
		// 获得物理内存
		if (rootJson.containsKey("windowsPhysicalMemory")) {
			cvWd.setConWindowsPhysicalMemory(rootJson.getInteger("windowsPhysicalMemory").longValue());
		}
		// 获得widnows配置信息
		if (rootJson.containsKey("windowsConfiguration")) {
			cvWd.setConWindowsConfig(rootJson.getString("windowsConfiguration"));
		}
		// 获得widnows接口数量
		if (rootJson.containsKey("windowsNumberNetworkInterfaces")) {
			cvWd.setConWindowsNumberNetworkInterfaces(
					Long.valueOf(rootJson.getString("windowsNumberNetworkInterfaces")));
		}
		// 获得C盘已使用多少GB
		if (rootJson.containsKey("windowsUsed_C_GB")) {
			cvWd.setConUsed_C(rootJson.getDouble("windowsUsed_C_GB"));
		}
		// 获得D盘已使用多少GB
		if (rootJson.containsKey("windowsUsed_D_GB")) {
			cvWd.setConUsed_D(rootJson.getDouble("windowsUsed_D_GB"));
		}
		// 获得E盘已使用多少GB
		if (rootJson.containsKey("windowsUsed_E_GB")) {
			cvWd.setConUsed_E(rootJson.getDouble("windowsUsed_E_GB"));
		}
		// 获得C盘总多少GB
		if (rootJson.containsKey("windowsHardDisk_C")) {
			cvWd.setConSumRam_C(rootJson.getDouble("windowsHardDisk_C"));
		}
		// 获得D盘总多少GB
		if (rootJson.containsKey("windowsHardDisk_D")) {
			cvWd.setConSumRam_D(rootJson.getDouble("windowsHardDisk_D"));
		}
		// 获得E盘总多少GB
		if (rootJson.containsKey("windowsHardDisk_E")) {
			cvWd.setConSumRam_E(rootJson.getDouble("windowsHardDisk_E"));
		}
		// 获得在线进程数
		if (rootJson.containsKey("windowsNumberProcesses")) {
			cvWd.setConWindowsNumberProcesses(Long.parseLong(rootJson.getString("windowsNumberProcesses")));
		}
		// 获得C盘剩余多少GB
		if (rootJson.containsKey("windowsBe_Usable_Double_C_GB")) {
			cvWd.setConBeUsabke_C(rootJson.getDouble("windowsBe_Usable_Double_C_GB"));
		}
		// 获得D盘剩余多少GB
		if (rootJson.containsKey("windowsBe_Usable_Double_D_GB")) {
			cvWd.setConBeUsabke_D(rootJson.getDouble("windowsBe_Usable_Double_D_GB"));
		}
		// 获得E盘剩余多少GB
		if (rootJson.containsKey("windowsBe_Usable_Double_E_GB")) {
			cvWd.setConBeUsabke_E(rootJson.getDouble("windowsBe_Usable_Double_E_GB"));
		}
	}
}
