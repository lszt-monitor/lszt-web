package com.lm.toos;

public interface Constants {
	//微信企业号调用发送信息给员工接口
	String POST_URL = "http://report.wsloan.com:8888/workweixin/api/Message/byWXid";

	// userSession
	String USER_SESSION = "userSession";
	// 分页页数
	int PAGE_SIEZ = 10;
	//upd请求
	 String DEFAULT_PROTOCOL = "udp";
	// 161 采集端
	int DEFAULT_PORT = 161;
	// 设置超时的时间
	long DEFAULT_TIMEOUT = 3 * 1000L; 
	// 实时推送时间 switch
	String SNMPSIZE = "0/30 * * * * ?";
	// 实时推送时间 windows
	int WINDOWSSIZE = 60000*5;
	// switch ip
	String[] SIP = new String[] { "192.168.10.131", "192.168.10.41", "192.168.10.51", "192.168.10.31","192.168.10.200","192.168.10.201"};

	//李山告警员工号
	String[] CAVEAT = new String[] { "L0280", "L1283","L0015","L0080","L0129","L1511","L0183"};

	//windows ip
	 String[] WIP = new String[] {"192.168.32.146", "192.168.2.218", "192.168.32.137", "192.168.33.107",
	"192.168.32.228", "192.168.32.170", "192.168.32.218","192.168.32.2","192.168.32.156"};

	//walk请求oid串----交换机
	String[] oidArrWalk = new String[] { ".1.3.6.1.4.1.171.12.1.1.8.1.2", ".1.3.6.1.4.1.171.12.1.1.8.1.3",
			".1.3.6.1.4.1.171.12.1.1.8.1.4",".1.3.6.1.4.1.171.12.1.1.9",".1.3.6.1.4.1.171.12.1.1.10",".1.3.6.1.4.1.171.12.1.1.6"};
	// 版本
	String COMMUNITY = "public";
	// 响应请求成功
	String HTTP_RES_CODE_200_VALUE = "success";
	// 系统错误
	String HTTP_RES_CODE_500_VALUE = "fial";
	// 响应请求成功code
	Integer HTTP_RES_CODE_200 = 200;
	// 系统错误
	Integer HTTP_RES_CODE = -1;


	// COOKIE
	int COOKIE_TOKEN_MEMBER_TIME = (60 * 60 * 24 * 3);

	// cookie token名称
	String COOKIE_MEMBER_TOKEN = "cookie_member_token";

	// fla 判断是否连接
	String SNMP_CONNECTION_FLG = "flg";


	// windows oid 获得 C 4096 硬盘
	String WINDOWS_TOTAL_MeBlock_C = "1.3.6.1.2.1.25.2.3.1.4.1";
	// windows oid 获得 D 4096 硬盘
	String WINDOWS_TOTAL_MeBlock_D = "1.3.6.1.2.1.25.2.3.1.4.2";
	// windows oid 获得 E 4096 硬盘
	String WINDOWS_TOTAL_MeBlock_E = "1.3.6.1.2.1.25.2.3.1.4.3";
	// windows oid 获得 E 4096 硬盘
	String WINDOWS_TOTAL_MeBlock_F = "1.3.6.1.2.1.25.2.3.1.4.4";

	// windows oid 获得 总的块 C 硬盘
	String WINDOWS_TOTAL_Block_C = "1.3.6.1.2.1.25.2.3.1.5.1";
	// windows oid 获得 总的块 D 硬盘
	String WINDOWS_TOTAL_Block_D = "1.3.6.1.2.1.25.2.3.1.5.2";
	// windows oid 获得 总的块 E 硬盘
	String WINDOWS_TOTAL_Block_E = "1.3.6.1.2.1.25.2.3.1.5.3";
	// windows oid 获得 总的块 E 硬盘
	String WINDOWS_TOTAL_Block_F = "1.3.6.1.2.1.25.2.3.1.5.4";

	// windows oid 得到盘使用的块 C
	String WINDOWS_USED_Block_C = "1.3.6.1.2.1.25.2.3.1.6.1";
	// windows oid 得到盘使用的块 D
	String WINDOWS_USED_Block_D = "1.3.6.1.2.1.25.2.3.1.6.2";
	// windows oid 得到盘使用的块 E
	String WINDOWS_USED_Block_E = "1.3.6.1.2.1.25.2.3.1.6.3";
	// windows oid 得到盘使用的块 E
	String WINDOWS_USED_Block_F = "1.3.6.1.2.1.25.2.3.1.6.4";
	
	// 获取台式机名字
	String WINDOWS_PC_NAME = "1.3.6.1.2.1.1.5.0";
	// 获取台式机上线时间
	String WINDOWS_PC_UP_Time = "1.3.6.1.2.1.1.3.0";
	// 获取台式机配置
	String WINDOWS_PC_CONfIGURATION = "1.3.6.1.2.1.1.1.0";
	// 获取台式机网络接口数目
	String WINDOWS_PC_NUMBERNETWORKINTERFACES = "1.3.6.1.2.1.2.1.0";

	// 物理内存计算 获得65536 1.3.6.1.2.1.25.2.3.1.4.5
	String WINDOWS_PC_PHYSICALMEMORYBLOCK = "1.3.6.1.2.1.25.2.3.1.4.5";

	// 1.3.6.1.2.1.25.2.3.1.6.5 物理内存已使用的块
	String WINDOWS_PC_USEDPHYSICALMEMORYBLOCK = "1.3.6.1.2.1.25.2.3.1.6.5";

	// 获得物理内存 总的块 1.3.6.1.2.1.25.2.2.0
	String WINDOWS_PC_SUMUSEDPHYSICALMEMORYBLOCK = "1.3.6.1.2.1.25.2.2.0";
	// 1.3.6.1.2.1.25.1.6.0 获得进程总数
	String WINDOWS_PC_NUMBER_OF_PROCESSES = "1.3.6.1.2.1.25.1.6.0";

}
