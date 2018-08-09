package com.lm.switchs.readinformationproperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.lm.base.BaseApiService;
import com.lm.mp.snmpversion.SnmpVersion;
import com.lm.toos.Constants;

@Component
// 如果有定时任务 不能+@ConfigurationProperties不然会执行2次
public class SwitchReadinformationProperties extends BaseApiService {

    // future多线程返回
    //private volatile Future<String> future;

    // snmp版本设置
    @Autowired
    private SnmpVersion snmpVersion;


    /**
     * snmp接收oid switch 3120 oid
     *
     * @throws IOException
     */
    public Future<String> switchGet(boolean pingFlg, String ip) {
        // dataJSON
        JSONObject dataSwitch = new JSONObject();
        if (!pingFlg) {
            dataSwitch.put("error", "pingno");
            return new AsyncResult<String>(dataSwitch.toJSONString());

        }
        JSONObject jsObj = new JSONObject();
        // 获得总的JSON value
        JSONObject switchJson = null;
        String switchWalkJson = null;
        try {
            //循环遍历oid请求
            for (int i = 0; i < Constants.oidArrWalk.length; i++) {
                switchWalkJson = snmpVersion.snmpConn().snmpAsynWalk(ip, Constants.COMMUNITY, Constants.oidArrWalk[i]);
                if (StringUtils.isNotEmpty(switchWalkJson)) {
                    // 转成json对象
                    switchJson = JSONObject.parseObject(switchWalkJson);
                    // 如果发现有个交换机返回是false 直接跳出这个循环下面
                    if (!switchJson.getBoolean("flg")) {
                        // NO 等于返回的是NULL
                        dataSwitch.put("error", "no");
                        return new AsyncResult<String>(dataSwitch.toJSONString());
                    }
                    // System.out.println(JSONObject.parseObject(switchWalkJson));
                    if (switchJson.containsKey("tmPortOid") && (switchJson.getBoolean("flg"))) {
                        jsObj.put("tmPort", switchJson);
                    }
                    if (switchJson.containsKey("rePortOid") && (switchJson.getBoolean("flg"))) {
                        jsObj.put("rePort", switchJson);
                    }
                    if (switchJson.containsKey("pePortOid") && (switchJson.getBoolean("flg"))) {
                        jsObj.put("pePort", switchJson);
                    }
                    if (switchJson.containsKey("dramValue") && (switchJson.getBoolean("flg"))) {
                        jsObj.put("dramData", switchJson);
                    }
                    if (switchJson.containsKey("cpuValue") && (switchJson.getBoolean("flg"))) {
                        jsObj.put("cpuData", switchJson);
                    }
                    if (switchJson.containsKey("flasValue") && (switchJson.getBoolean("flg"))) {
                        jsObj.put("flasData", switchJson);
                    }
                }
            }

            if (jsObj != null) {
                dataSwitch.put("data", jsObj);
                return new AsyncResult<String>(dataSwitch.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /*public static void main(String[] args) {
        String[] ipAddress = new String[]{"192.168.10.31","192.168.10.131","192.168.10.51","192.168.10.10"};
       for (int i =0; i< ipAddress.length;i++){
           System.out.println(ping(ipAddress[i], 20, 20));
       }
    }*/
}
