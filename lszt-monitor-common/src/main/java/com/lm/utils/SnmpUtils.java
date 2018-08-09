package com.lm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import com.alibaba.fastjson.JSONObject;
import com.lm.toos.Constants;
import com.lm.toos.E_Oid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnmpUtils {
        private Logger log = LoggerFactory.getLogger(this.getClass());
    //自定义日志
    private Logger connectionLogger = LoggerFactory.getLogger("connectionLogger");

    //自定义日志
    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");
    /**
     * @params version snmp 版本号
     */
    private int version;

    private volatile boolean flg = false;// 判断是否已连接


    /**
     * @param ip        windows采集 GET
     * @param community
     * @param targetOID
     * @return
     */
    public String setWindowsAsynGet(String ip, String community, List<String> targetOID) {
        return asynGetList(ip, community, targetOID);
    }

    /**
     * windows walk查询磁盘的总数
     *
     * @param ip
     * @param community
     * @param oid
     * @return
     */
    public String setWindowsAsynWalk(String ip, String community, String oid) {
        return snmpAsynWalk(ip, community, oid);
    }

    /**
     * 创建SNMP管理对象
     *
     * @param version
     */
    public SnmpUtils(int version) {
        this.version = version;
    }

    /**
     * snmp请求头
     */
    public Target vsTarget(String ip, String community) {
        Target target = null;
        target = new CommunityTarget();
        if (version == SnmpConstants.version1) {
            target.setVersion(SnmpConstants.version1);
            ((CommunityTarget) target).setCommunity(new OctetString(community));
        } else {
            target.setVersion(SnmpConstants.version2c);
            ((CommunityTarget) target).setCommunity(new OctetString(community));
        }
        Address address = GenericAddress.parse(Constants.DEFAULT_PROTOCOL + ":" + ip + "/" + Constants.DEFAULT_PORT);
        target.setAddress(address);
        target.setTimeout(Constants.DEFAULT_TIMEOUT); // milliseconds
        return target;
    }

    /**
     * Walk
     *
     * @param targetOID
     * @return
     */
    public PDU getWalk(OID targetOID) {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(targetOID));
        return pdu;

    }

    /**
     * GET
     *
     * @return
     */
    public PDU getPdu(List<String> oidList) {
        PDU pdu = new PDU();
        for (String oid : oidList) {
            pdu.add(new VariableBinding(new OID(oid)));
        }
        // 设置请求
        pdu.setType(PDU.GET);
        return pdu;
    }

    /**
     * 封装Snmp
     *
     * @return
     */
    public Snmp startListen() {
        Snmp snmp = null;
        try {
            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return snmp;
    }

    /**
     * 根据OID列表，采用异步方式一次获取多条OID数据，并且以List形式返回 WINDOWS
     *
     * @param ip
     * @param community
     * @param oidList
     * @return
     */
    public String asynGetList(String ip, String community, List<String> oidList) {
        final JSONObject jsObject = new JSONObject();
        Snmp snmp = null;
        try {
            // 封装target
            Target target = vsTarget(ip, community);
            // 封装PDU
            PDU pdu = getPdu(oidList);
            // 封装Listen监听
            snmp = startListen();

            final CountDownLatch latch = new CountDownLatch(1);
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    try {
                        PDU response = event.getResponse();
                        if (response == null) {
                            connectionLogger.info(ip + "::::::"+ "ERROR Walk 连接 response=NUll");
                            flg = false;
                            jsObject.put(Constants.SNMP_CONNECTION_FLG, flg);
                        } else if (response.getErrorStatus() != 0) {
                            connectionLogger.info(ip + ":" + "[错误]：响应状态:" + response.getErrorStatus() + " Text:"
                                    + response.getErrorStatusText());
                        } else {
                            log.info("正在接收SNMP台式机Get数据:");
                            if (response.getErrorStatus() == PDU.noError) {
                                @SuppressWarnings("unchecked")
                                List<? extends VariableBinding> vbs = response.getVariableBindings();
                                vbs.forEach(vb -> jsObject.put(vb.getOid().toString(), vb.getVariable().toString()));

                                for (VariableBinding vb : vbs) {
                                    dataLogger.info(ip + "\t" + "`oid={}`gV={}`", vb.getOid(),
                                            vb.getVariable());
                                    jsObject.put(vb.getOid().toString(), vb.getVariable().toString());
                                }
                            } else {
                                log.info("Error:" + response.getErrorStatusText());
                                latch.countDown();
                            }
                            /*
                             * fla = true; jSONObject.put(Constants.SNMP_CONNECTION_FLG, fla);
                             */
                            latch.countDown();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }
                }
            };
            snmp.send(pdu, target, null, listener);
            boolean wait = latch.await(10, TimeUnit.SECONDS);
            log.info(ip + ":wait:" + wait + ":windowsAsynGetList");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
        return jsObject.toJSONString();
    }

    /**
     * 根据targetOID，异步获取树形数据
     *
     * @param ip
     * @param community
     * @param oid
     */
    public String snmpAsynWalk(String ip, String community, String oid) {
        final List<String> vbValue = new ArrayList<String>();
        final List<String> vbOid = new ArrayList<String>();
        final JSONObject jsObject = new JSONObject();
        Target target = vsTarget(ip, community);
        Snmp snmp = null;
        try {
            final OID targetOID = new OID(oid);
            // 封装tWalkPDU
            final PDU pdu = getWalk(targetOID);
            // 封装Listen监听
            snmp = startListen();
            final CountDownLatch latch = new CountDownLatch(1);
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    try {
                        PDU response = event.getResponse();
                        if (response == null) {
                            connectionLogger.info(ip + "::::::" + oid + "ERROR Walk 连接 response=NUll");
                            flg = false;
                            jsObject.put(Constants.SNMP_CONNECTION_FLG, flg);
                        } else if (response.getErrorStatus() != 0) {
                            connectionLogger.info("ERROR" + response.getErrorStatus() + " Text:" + response.getErrorStatusText());
                        } else {
                            VariableBinding vb = response.get(0);
                            boolean finished = checkWalkFinishedWalk(targetOID, pdu, vb);
                            if (!finished) {
                                dataLogger.info(ip + "\t" + "`oid={}`wV={}`", vb.getOid(),
                                        vb.getVariable());
                                swtichOid(oid, vbValue, vbOid, jsObject, vb);
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                ((Snmp) event.getSource()).getNext(pdu, target, null, this);
                            } else {
                                log.info(ip + ":Walk OID success!");
                                flg = true;
                                jsObject.put(Constants.SNMP_CONNECTION_FLG, flg);
                                latch.countDown();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }
                }
            };
            snmp.getNext(pdu, target, null, listener);
            //等待5秒
            boolean wait = latch.await(10, TimeUnit.SECONDS);
            log.info("latch.await =:" + wait);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Walk Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
        }
        return jsObject.toJSONString();
    }

    /**
     * Walk Oid判断
     *
     * @param targetOID
     * @param pdu
     * @param vb
     * @return
     */
    private boolean checkWalkFinishedWalk(OID targetOID, PDU pdu, VariableBinding vb) {
        boolean finished = false;
        if (pdu.getErrorStatus() != 0) {
            // log.info("[true] responsePDU.getErrorStatus() != 0 ");
            // log.info(pdu.getErrorStatusText());
            finished = true;
        } else if (vb.getOid() == null) {
            // log.info("[true] vb.getOid() == null");
            finished = true;
        } else if (vb.getOid().size() < targetOID.size()) {
            // log.info("[true] vb.getOid().size() < targetOID.size()");
            finished = true;
        } else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {
            // log.info("[true] targetOID.leftMostCompare() != 0");
            finished = true;
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
            // log.info("[true] Null.isExceptionSyntax(vb.getVariable().getSyntax())");
            finished = true;
        } else if (vb.getOid().compareTo(targetOID) <= 0) {
            // log.info("[true] Variable received is not " + "lexicographic successor of
            // requested " + "one:");
            // log.info(vb.toString() + " <= " + targetOID);
            finished = true;
        }
        return finished;

    }


   /*
    public String snmpWalk(String ip, String community, String targetOid) {
        final List<String> vbValue = new ArrayList<String>();
        final List<String> vbOid = new ArrayList<String>();
        final JSONObject jsObject = new JSONObject();
        Target target = vsTarget(ip, community);
        TransportMapping transport = null;
        Snmp snmp = null;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();
            PDU pdu = new PDU();
            OID targetOID = new OID(targetOid);
            pdu.add(new VariableBinding(targetOID));

            System.out.println("----> demo start <----");
            while (!flg) {
                VariableBinding vb = null;
                ResponseEvent respEvent = snmp.getNext(pdu, target);

                PDU response = respEvent.getResponse();

                if (null == response) {
                    System.out.println("responsePDU == null");
                    flg = false;
                    jsObject.put(Constants.SNMP_CONNECTION_FLG, flg);
                    break;
                } else {
                    vb = response.get(0);
                }
                // check finish
                flg = checkWalkFinishedWalk(targetOID, pdu, vb);
                if (!flg) {
                    swtichOid(targetOid, vbValue, vbOid, jsObject, vb);
                    dataLogger.info(ip + "\t" + "`oid={}`wV={}`", vb.getOid(),
                            vb.getVariable());
                    // Set up the variable binding for the next entry.
                    pdu.setRequestID(new Integer32(0));
                    pdu.set(0, vb);
                } else {
                    System.out.println("SNMP walk OID has finished.");
                    flg = true;
                    jsObject.put(Constants.SNMP_CONNECTION_FLG, flg);
                    snmp.close();
                }
            }
            System.out.println("----> demo end <----");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP walk Exception: " + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
        }
         return  jsObject.toJSONString();
    }
*/
    /**
     * 封装对应的oid解析
     *
     * @param oid
     * @param vbValue
     * @param vbOid
     * @param vb
     */
    public void swtichOid(String oid, List<String> vbValue, List<String> vbOid, JSONObject jsObject,
                          VariableBinding vb) {
        // 传输帧速率OID
        if (oid.equals(E_Oid.TMPORT.getValue())) {
            // log.info(ip + "\t" + "key:{}" + "\t" + "value:{}", vb.getOid().toString(),
            // vb.getVariable().toString());
            addList(vbValue, vbOid, vb);
            jsObject.put("tmPortValue", vbValue);
            jsObject.put("tmPortOid", vbOid);
        }
        // 接收帧速率OID
        if (oid.equals(E_Oid.REPORT.getValue())) {
            // log.info(ip + "\t" + "key:{}" + "\t" + "value:{}", vb.getOid().toString(),
            // vb.getVariable().toString());
            addList(vbValue, vbOid, vb);
            jsObject.put("rePortValue", vbValue);
            jsObject.put("rePortOid", vbOid);
        }
        // 目前端口统计的百分比OID
        if (oid.equals(E_Oid.PEPORT.getValue())) {
            addList(vbValue, vbOid, vb);
            jsObject.put("pePortValue", vbValue);
            jsObject.put("pePortOid", vbOid);
        }

        // CPU使用3种状态OID
        if (oid.equals(E_Oid.CPU.getValue())) {
            // log.info(ip + "\t" + "key:{}" + "\t" + "value:{}", vb.getOid().toString(),
            // vb.getVariable().toString());
            addList(vbValue, vbOid, vb);
            jsObject.put("cpuValue", vbValue);
            jsObject.put("cputOid", vbOid);
        }
        // windows发现硬盘有多少OID
        if (oid.equals(E_Oid.HRPARTITIONIMDEX.getValue())) {
            vbValue.add(vb.getVariable().toString());
            jsObject.put("hrPartitionIndex", vbValue);
        }
        // 获得RAM内存的3种状态
        if (oid.equals(E_Oid.DRAMVALUE.getValue())) {
            addList(vbValue, vbOid, vb);
            jsObject.put("dramValue", vbValue);
            jsObject.put("dramOid", vbOid);
        }
        // 获得Fals内存的3种状态
        if (oid.equals(E_Oid.FLASVALUE.getValue())) {
            addList(vbValue, vbOid, vb);
            jsObject.put("flasValue", vbValue);
            jsObject.put("flasOid", vbOid);
        }
    }

    /**
     * ListADD封装
     *
     * @param vbValue
     * @param vbOid
     * @param vb
     */
    public void addList(List<String> vbValue, List<String> vbOid, VariableBinding vb) {
        vbValue.add(vb.getVariable().toString());
        vbOid.add(vb.getOid().toString());
    }


    public static void main(String[] args) {
        SnmpUtils s = new SnmpUtils(2);
       // s.snmpAsynWalk("192.168.10.131", "public", ".1.3.6.1.4.1.171.12.1.4");
        //s.snmpGetBulk("192.168.10.31","public",".1.3.6.1.4.1.171.12.5.1.2");
        //List<String> oid =new ArrayList<>();
       // s.snmpWalk("192.168.10.31", "public", ".1.3.6.1.4.1.171.12.1.1.6");
    }
}
