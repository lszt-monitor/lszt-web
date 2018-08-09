package com.lm.windows.readinformationproperties;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.lm.api.model.windows.WindowsGb;
import com.lm.mp.snmpversion.SnmpVersion;
import com.lm.toos.Constants;
import com.lm.utils.AlgorithmUtils;

/**
 * windows 监控机配置
 * 
 * @author Administrator
 *
 */
@Component
@PropertySource("classpath:windows.properties")
public class WindowsReadinformationPropertiesGet {
	// future多线程返回
	private volatile Future<String> future;
	// 获取drive_C盘 变量
	long drive_L_C = 0;
	// 获得C总的块 变量
	long sumDrive_C = 0;
	// driveC盘 变量
	long drive_L_D;

	long sumDrive_D;
	// driveC盘 变量
	long drive_L_E;
	long sumDrive_E;
	// driveC盘 变量
	long drive_L_F;
	long sumDrive_F;
	// C盘已经用过的变量
	long used_C_L_V;
	// D盘已经用过的变量
	long used_D_L_V;
	// C盘使用总的GB
	String str_C_GB = null;
	// D盘使用总的GB
	String str_D_GB = null;
	// E盘使用总的GB
	String str_E_GB = null;
	// 截取后的字符串C盘总GB
	String su_Str_C_Gb = null;
	// 物理内存块 65536
	long l_PhysicalMemoryBlock;
	// 物理内存已使用的块
	long l_UsedPhysicalMemoryBlock;
	// 获得物理内存总的块
	long l_SumPhysicalMemoryBlock;

	@Value("${drive.C.block}")
	private String drive_C_block;
	@Value("${drive.D.block}")
	private String drive_D_block;
	@Value("${drive.E.block}")
	private String drive_E_block;
	@Value("${drive.F.block}")
	private String drive_F_block;
	@Value("${number4.65536}")
	private String number4_65536;
	@Value("${number3.65536}")
	private String number3_65536;
	@Value("${number2.65536}")
	private String number2_65536;
	@Value("${computer.name}")
	private String omputer_Name;
	@Value("${computer.open.time}")
	private String computer_Open_Time;
	@Value("${computer.info}")
	private String computer_Info;
	@Value("${internet.internet.number}")
	private String internet_Internet_Number;
	@Value("${physical.memory}")
	private String physical_Memory;
	@Value("${drive.C}")
	private String drive_C;
	@Value("${drive.D}")
	private String drive_D;
	@Value("${drive.F}")
	private String drive_F;
	@Value("${drive.E}")
	private String drive_E;
	@Value("${drive.H}")
	private String drive_H;
	@Value("${used.C}")
	private String used_C;
	@Value("${used.D}")
	private String used_D;
	@Value("${used.E}")
	private String used_E;
	@Value("${used.F}")
	private String used_F;
	@Value("${used.physical.memory_2}")
	private String used_physical_memory_2;
	@Value("${used.physical.memory_3}")
	private String used_physical_memory_3;
	@Value("${used.physical.memory_4}")
	private String used_physical_memory_4;
	@Value("${number.of.processes}")
	private String number_Of_Processes;

	// snmp版本设置
	@Autowired
	private SnmpVersion snmpVersion;

	// 3个盘List
	public List<String> oidWsList3() {
		// 存储 swtich oid
		List<String> windows_Oids = new ArrayList<String>();
		// 设置OID targetOID.add
		windows_Oids.add(drive_C_block);
		windows_Oids.add(drive_D_block);
		windows_Oids.add(drive_E_block);
		windows_Oids.add(number3_65536);
		windows_Oids.add(omputer_Name);
		windows_Oids.add(computer_Open_Time);
		windows_Oids.add(computer_Info);
		windows_Oids.add(internet_Internet_Number);
		windows_Oids.add(physical_Memory);
		windows_Oids.add(drive_C);
		windows_Oids.add(drive_D);
		windows_Oids.add(drive_E);
		windows_Oids.add(used_C);
		windows_Oids.add(used_D);
		windows_Oids.add(used_E);
		windows_Oids.add(used_physical_memory_3);
		windows_Oids.add(number_Of_Processes);
		return windows_Oids;
	}

	// 4个盘List
	public List<String> oidWsList4() {
		// 存储 swtich oid
		List<String> windows_Oids = new ArrayList<String>();
		// 设置OID targetOID.add
		windows_Oids.add(drive_C_block);
		windows_Oids.add(drive_D_block);
		windows_Oids.add(drive_E_block);
		windows_Oids.add(drive_F_block);
		windows_Oids.add(number4_65536);
		windows_Oids.add(omputer_Name);
		windows_Oids.add(computer_Open_Time);
		windows_Oids.add(computer_Info);
		windows_Oids.add(internet_Internet_Number);
		windows_Oids.add(physical_Memory);
		windows_Oids.add(drive_C);
		windows_Oids.add(drive_D);
		windows_Oids.add(drive_E);
		windows_Oids.add(drive_F);
		windows_Oids.add(used_C);
		windows_Oids.add(used_D);
		windows_Oids.add(used_E);
		windows_Oids.add(used_F);
		windows_Oids.add(used_physical_memory_4);
		windows_Oids.add(number_Of_Processes);
		return windows_Oids;
	}

	// 2个盘List
	public List<String> oidWsList2() {
		// 存储 swtich oid
		List<String> windows_Oids = new ArrayList<String>();
		// 设置OID targetOID.add
		windows_Oids.add(drive_C_block);
		windows_Oids.add(drive_D_block);
		windows_Oids.add(number2_65536);
		windows_Oids.add(omputer_Name);
		windows_Oids.add(computer_Open_Time);
		windows_Oids.add(computer_Info);
		windows_Oids.add(internet_Internet_Number);
		windows_Oids.add(physical_Memory);
		windows_Oids.add(drive_C);
		windows_Oids.add(drive_D);
		windows_Oids.add(used_C);
		windows_Oids.add(used_D);
		windows_Oids.add(used_physical_memory_2);
		windows_Oids.add(number_Of_Processes);
		return windows_Oids;
	}

	// 判断长度 得知几个盘数返回对应的ListOid 集合
	public List<String> sizeList(List<Object> sz) {
		if (sz.size() == 5) {
			return oidWsList3();
		}
		if (sz.size() == 4) {
			return oidWsList2();
		}
		if (sz.size() == 6) {
			return oidWsList4();
		}
		return null;
	}

	// walk请求oid串
	String[] oidArrWalk = new String[] { ".1.3.6.1.2.1.25.4.2.1.2", ".1.3.6.1.2.1.25.2.3.1.1" };

	/**
	 * Windows实时推送
	 * 
	 * @throws IOException
	 */
	public Future<String> windowsGet(String ip, JSONObject newJsonObj) throws IOException {
		// 获得List集合
		List<String> targetOIdList = null;
		// 获得List长度
		List<Object> szList = null;
		JSONObject strJson = null;
		JSONObject jsObj = null;
		boolean flgTc = false;
		try {
			for (int i = 0; i < oidArrWalk.length; i++) {
				String sizeStr = snmpVersion.snmpConn().setWindowsAsynWalk(ip, Constants.COMMUNITY, oidArrWalk[i]);
				if (StringUtils.isNotEmpty(sizeStr)) {
					strJson = JSONObject.parseObject(sizeStr);
					if (strJson.getBoolean("flg") == false) {
						// NO 等于连接不上交换机
						newJsonObj.put("data", "no");
						return new AsyncResult<String>(newJsonObj.toJSONString());
					}
					if (strJson.containsKey("hrPartitionIndex")) {
						szList = strJson.getJSONArray("hrPartitionIndex");
						targetOIdList = sizeList(szList);
					}
					if (strJson.containsKey("processValue")) {
						newJsonObj.put("pc_Process", strJson.getJSONArray("processValue"));
					}
					if (strJson.containsKey(Constants.SNMP_CONNECTION_FLG)) {
						flgTc = strJson.getBoolean(Constants.SNMP_CONNECTION_FLG);// 判断是否正常连接
						newJsonObj.put("windowsflg", flgTc);
						newJsonObj.put("ip", ip);
					}
				}
			}
			if (flgTc) {
				// 得到Json
				String JsonRoot = snmpVersion.snmpConn().setWindowsAsynGet(ip, Constants.COMMUNITY, targetOIdList);
				if (StringUtils.isNotEmpty(JsonRoot)) {
					// 转换Json对象
					jsObj = JSONObject.parseObject(JsonRoot);// 转成json对象
				}
				// 获取台式机名字
				if (jsObj.containsKey(Constants.WINDOWS_PC_NAME)) {
					String pcName = jsObj.getString(Constants.WINDOWS_PC_NAME);
					newJsonObj.put("windowsName", pcName);
				}
				// 1.3.6.1.2.1.25.1.6.0 获得进程总数
				if (jsObj.containsKey(Constants.WINDOWS_PC_NUMBER_OF_PROCESSES)) {
					String number_Of_Processes = jsObj.getString(Constants.WINDOWS_PC_NUMBER_OF_PROCESSES);
					newJsonObj.put("windowsNumberProcesses", number_Of_Processes);
				}
				// 获取台式机上线时间
				if (jsObj.containsKey(Constants.WINDOWS_PC_UP_Time)) {
					String pcUpTime = jsObj.getString(Constants.WINDOWS_PC_UP_Time);
					newJsonObj.put("windowsTime", pcUpTime);
				}
				// 获取台式机配置
				if (jsObj.containsKey(Constants.WINDOWS_PC_CONfIGURATION)) {
					String pcConfiguration = jsObj.getString(Constants.WINDOWS_PC_CONfIGURATION);
					newJsonObj.put("windowsConfiguration", pcConfiguration);
				}
				// 获取台式机网络接口数目
				if (jsObj.containsKey(Constants.WINDOWS_PC_NUMBERNETWORKINTERFACES)) {
					String pcNumberNetworkInterfaces = jsObj.getString(Constants.WINDOWS_PC_NUMBERNETWORKINTERFACES);
					newJsonObj.put("windowsNumberNetworkInterfaces", pcNumberNetworkInterfaces);
				}
				// 物理内存使用计算
				windowPhysical(jsObj, newJsonObj);
				if (jsObj.containsKey("1.3.6.1.2.1.25.2.3.1.5.1")) {
					// C盘计算;
					windowsRam(ip, Constants.WINDOWS_TOTAL_MeBlock_C, Constants.WINDOWS_TOTAL_Block_C,
							Constants.WINDOWS_USED_Block_C, jsObj, newJsonObj);
				}
				if (jsObj.containsKey("1.3.6.1.2.1.25.2.3.1.5.2")) {
					// D盘计算
					windowsRam(ip, Constants.WINDOWS_TOTAL_MeBlock_D, Constants.WINDOWS_TOTAL_Block_D,
							Constants.WINDOWS_USED_Block_D, jsObj, newJsonObj);
				}
				if (jsObj.containsKey("1.3.6.1.2.1.25.2.3.1.5.3")) {
					// E盘计算
					windowsRam(ip, Constants.WINDOWS_TOTAL_MeBlock_E, Constants.WINDOWS_TOTAL_Block_E,
							Constants.WINDOWS_USED_Block_E, jsObj, newJsonObj);
				}
				// F盘计算
				if (jsObj.containsKey("1.3.6.1.2.1.25.2.3.1.5.4")) {
					// E盘计算
					windowsRam(ip, "1.3.6.1.2.1.25.2.3.1.5.4", Constants.WINDOWS_USED_Block_F,
							Constants.WINDOWS_USED_Block_F, jsObj, newJsonObj);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		future = new AsyncResult<String>(newJsonObj.toJSONString());
		return future;
	}

	/**
	 * 获得物理内存使用率计算
	 * 
	 * @param jsObj
	 * @param newJsonObj
	 */
	public void windowPhysical(JSONObject jsObj, JSONObject newJsonObj) {
		// 物理内存计算 获得65536 1.3.6.1.2.1.25.2.3.1.4.5
		if (jsObj.containsKey(Constants.WINDOWS_PC_PHYSICALMEMORYBLOCK)) {
			String physicalMemoryBlock = jsObj.getString(Constants.WINDOWS_PC_PHYSICALMEMORYBLOCK);
			l_PhysicalMemoryBlock = Long.valueOf(physicalMemoryBlock);
		}
		// 1.3.6.1.2.1.25.2.3.1.6.5 物理内存已使用的块
		if (jsObj.containsKey(Constants.WINDOWS_PC_USEDPHYSICALMEMORYBLOCK)) {
			String usedPhysicalMemoryBlock = jsObj.getString(Constants.WINDOWS_PC_USEDPHYSICALMEMORYBLOCK);
			l_UsedPhysicalMemoryBlock = Long.valueOf(usedPhysicalMemoryBlock);
		}
		// 获得物理内存 总的块 1.3.6.1.2.1.25.2.2.0
		if (jsObj.containsKey(Constants.WINDOWS_PC_SUMUSEDPHYSICALMEMORYBLOCK)) {
			String sumPhysicalMemoryBlock = jsObj.getString(Constants.WINDOWS_PC_SUMUSEDPHYSICALMEMORYBLOCK);
			l_SumPhysicalMemoryBlock = Long.valueOf(sumPhysicalMemoryBlock);
		}
		if (l_UsedPhysicalMemoryBlock != 0 & l_PhysicalMemoryBlock != 0) {
			// 物理内存占用率：(65536*45513/1024)/4088864*100%=71.24%
			double dbBlock = ((l_UsedPhysicalMemoryBlock * l_PhysicalMemoryBlock / 1024));
			String physicalMemory = AlgorithmUtils.accuracy(dbBlock, l_SumPhysicalMemoryBlock, 0);
			int it_physicalMemory = Integer.valueOf(physicalMemory);
			newJsonObj.put("windowsPhysicalMemory", it_physicalMemory);
		}
	}

	/**
	 * windows盘计算GB
	 * 
	 * @param ip
	 * @param jsObj
	 * @param newJsonObj
	 */
	public void windowsRam(String ip, String totalMeBlock, String totalBlock, String usedBlock, JSONObject jsObj,
			JSONObject newJsonObj) {
		// C: 盘大小为4096×20972849=85904789504bytes or 80GB
		// C: 盘以用空间4096×7389539=7566887936bytes or 7.04GB
		// C： 盘使用率为7.04/80*100% = 8%
		// 获得对象
		WindowsGb ws = null;
		// 获得盘箸/块 "1.3.6.1.2.1.25.2.3.1.4.1"
		if (jsObj.containsKey(totalMeBlock)) {
			String drive_C_Block = jsObj.getString(totalMeBlock);
			if (StringUtils.isNotEmpty(drive_C_Block)) {
				// 获得C 4096
				Integer drive_C_Block_Value = Integer.valueOf(drive_C_Block);
				drive_L_C = drive_C_Block_Value;
			}
		}
		// 获得盘总箸/块 "1.3.6.1.2.1.25.2.3.1.5.1"
		if (jsObj.containsKey(totalBlock)) {
			String drive_C = jsObj.getString(totalBlock);
			if (StringUtils.isNotEmpty(drive_C)) {
				// 获得总的块
				Integer drive_C_Value = Integer.valueOf(drive_C);
				sumDrive_C = drive_C_Value;
			}
		}
		// 获得盘总数GB
		if (drive_L_C != 0 & sumDrive_C != 0) {
			String str_C = String.valueOf(drive_L_C * sumDrive_C);
			// 获得总的盘 GB数
			str_C_GB = AlgorithmUtils.getPrintSize(Long.parseLong(str_C));
			// 获得截取后的盘 GB
			su_Str_C_Gb = str_C_GB.substring(0, str_C_GB.length() - 2);
		}
		// 获得盘已用大小"1.3.6.1.2.1.25.2.3.1.6.1"
		if (jsObj.containsKey(usedBlock)) {
			String used_C = jsObj.getString(usedBlock);
			if (StringUtils.isNotEmpty(used_C)) {
				Integer used_C_Value = Integer.valueOf(used_C);
				// 得到盘使用的块
				used_C_L_V = used_C_Value;
				// 判断2个值是否为0
				if (used_C_L_V != 0 & drive_L_C != 0) {
					// 获得盘 已使用的GB
					String str_Used_C = String.valueOf(drive_L_C * used_C_L_V);
					String str_Used_C_GB = AlgorithmUtils.getPrintSize(Long.parseLong(str_Used_C));
					ws = windowsSwitch(ip, str_Used_C_GB, newJsonObj);
				}
			}
		}
		if (totalMeBlock.equals(Constants.WINDOWS_TOTAL_MeBlock_C)) {
			// 总的GB 放入JSON
			newJsonObj.put("windowsHardDisk_C", ws.getHardDisk());
			// 用过的
			newJsonObj.put("windowsUsed_C_GB", ws.getUsed_GB());
			// 可用
			newJsonObj.put("windowsBe_Usable_Double_C_GB", AlgorithmUtils.dblast(ws.getBe_Usable_Double_GB()));
		}
		if (totalMeBlock.equals(Constants.WINDOWS_TOTAL_MeBlock_D)) {
			// 总的GB 放入JSON
			newJsonObj.put("windowsHardDisk_D", ws.getHardDisk());
			// 用过的
			newJsonObj.put("windowsUsed_D_GB", ws.getUsed_GB());
			// 可用
			newJsonObj.put("windowsBe_Usable_Double_D_GB", AlgorithmUtils.dblast(ws.getBe_Usable_Double_GB()));
		}
		if (totalMeBlock.equals(Constants.WINDOWS_TOTAL_MeBlock_E)) {
			// 总的GB 放入JSON
			newJsonObj.put("windowsHardDisk_E", ws.getHardDisk());
			// 用过的
			newJsonObj.put("windowsUsed_E_GB", ws.getUsed_GB());
			// 可用
			newJsonObj.put("windowsBe_Usable_Double_E_GB", AlgorithmUtils.dblast(ws.getBe_Usable_Double_GB()));
		}
		if (totalMeBlock.equals("1.3.6.1.2.1.25.2.3.1.4.4")) {
			// 总的GB 放入JSON
			newJsonObj.put("windowsHardDisk_F", ws.getHardDisk());
			// 用过的
			newJsonObj.put("windowsUsed_F_GB", ws.getUsed_GB());
			// 可用
			newJsonObj.put("windowsBe_Usable_Double_F_GB", AlgorithmUtils.dblast(ws.getBe_Usable_Double_GB()));
		}
	}

	/**
	 * 封装switch选择
	 * 
	 * @param str_Used_C_GB
	 * @param newJsonObj
	 */
	public WindowsGb windowsSwitch(String ip, String str_Used_C_GB, JSONObject newJsonObj) {
		// 获取str_Used_C_GB长度后面的2位置来判断是什么类型
		switch (str_Used_C_GB.substring(str_Used_C_GB.length() - 2)) {
		case "GB":
			return windows_GB(ip, str_Used_C_GB, newJsonObj);
		case "MB":
			return windows_MB(ip, str_Used_C_GB, newJsonObj);
		case "KB":

			break;
		case "B":

			break;
		}
		return null;
	}

	/**
	 * 计算GB方法
	 * 
	 * @param str_Used_C_GB
	 * @param newJsonObj
	 */
	public WindowsGb windows_GB(String ip, String str_Used_C_GB, JSONObject newJsonObj) {
		WindowsGb wg = new WindowsGb();
		// 截掉末尾2个字符串 转成double类型
		String db_str_Used_C_GB = str_Used_C_GB.substring(0, str_Used_C_GB.length() - 2);
		// 获取已使用的盘GB
		double db_Used_C_GB = Double.parseDouble(db_str_Used_C_GB);
		// 转换总的盘GB
		double db_str_C_GB = Double.parseDouble(su_Str_C_Gb);
		// 获得得到盘的可用GB数据
		double be_Usable_Double_C = (db_str_C_GB - db_Used_C_GB);
		wg.setHardDisk(db_str_C_GB);
		wg.setBe_Usable_Double_GB(be_Usable_Double_C);
		wg.setUsed_GB(db_Used_C_GB);
		return wg;
	}

	/**
	 * 计算MB方法
	 * 
	 * @param str_Used_C_GB
	 * @param newJsonObj
	 */
	public WindowsGb windows_MB(String ip, String str_Used_C_GB, JSONObject newJsonObj) {
		WindowsGb wg = new WindowsGb();
		// 截掉末尾2个字符串 转成double类型
		String db_str_Used_C_MB = str_Used_C_GB.substring(0, str_Used_C_GB.length() - 2);
		// 获取已使用的C盘GB
		double db_Used_C_GB_MB = Double.parseDouble(db_str_Used_C_MB);
		// 将mb转换成GB
		double db_New_C_GB = (db_Used_C_GB_MB / 1024);
		// 获取double .后面只保留2位数 获得已用数据
		double auDb = AlgorithmUtils.dblast(db_New_C_GB);
		// 转换总的盘GB
		double db_str_C_GB_MB = Double.parseDouble(su_Str_C_Gb);
		// 获得得到盘的可使用GB数据
		double be_Usable_Double_C_MB = (db_str_C_GB_MB - auDb);
		wg.setHardDisk(db_str_C_GB_MB);
		wg.setBe_Usable_Double_GB(be_Usable_Double_C_MB);
		wg.setUsed_GB(auDb);
		return wg;
	}

}
