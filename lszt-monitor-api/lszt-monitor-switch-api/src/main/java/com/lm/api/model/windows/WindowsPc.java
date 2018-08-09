package com.lm.api.model.windows;

import java.util.Date;

public class WindowsPc extends PetWindowsPc {


	public WindowsPc(String ip, Integer w_Id, double beUsabke_C, double beUsabke_D, double beUsabke_E,
			double beUsabke_F, String windowsName, String windowsConfig, double sumRam_C, double sumRam_D,
			double sumRam_E, double sumRam_F, Date winodwsUpTime, Long windowsNumberProcesses,
			Long windowsPhysicalMemory, Long windowsNumberNetworkInterfaces, double used_C, double used_D,
			double used_E, double used_F, int windowsFlg) {
			super(ip, windowsFlg, used_F, used_F, used_F, used_F,
					windowsConfig, windowsConfig, used_F, used_F, used_F,
					used_F, winodwsUpTime, windowsNumberNetworkInterfaces,
					windowsNumberNetworkInterfaces, windowsNumberNetworkInterfaces,
					used_F, used_F, used_F, used_F, windowsFlg);
	}

}
