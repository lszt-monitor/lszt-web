package com.lm.mp.snmpversion;
import org.snmp4j.mp.SnmpConstants;
import org.springframework.stereotype.Component;
import com.lm.utils.SnmpUtils;
@Component
public class SnmpVersion {
	
	// 设置版本号方法
	public SnmpUtils snmpConn(){
		SnmpUtils snmpConnter = null;
		snmpConnter = new SnmpUtils(SnmpConstants.version2c);// 设置版号
		return snmpConnter;
	}

}
