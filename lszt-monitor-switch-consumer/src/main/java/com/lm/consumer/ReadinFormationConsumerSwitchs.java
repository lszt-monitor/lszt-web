package com.lm.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lm.api.model.Caveat;
import com.lm.base.BaseApiService;
import com.lm.service.switchsImpl.*;
import com.lm.toos.Constants;
import com.lm.utils.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lm.api.model.cpu.Cpu;
import com.lm.api.model.darm.Dram;
import com.lm.api.model.flas.Flas;
import com.lm.api.model.port.OutPutPort;
import com.lm.api.model.port.PetPort;
import com.lm.api.model.port.ReceivePort;
import com.lm.api.model.port.UtilPort;
import com.lm.base.ResponseBase;


@Component
public class ReadinFormationConsumerSwitchs extends BaseApiService {


    private Logger log = LoggerFactory.getLogger(this.getClass());
    // 自定义日志
    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");
    // 父类端口
    private PetPort petPort;
    // tm
    @Autowired
    private OutPutPortServiceImpl outPutPortMapperImpl;
    // re
    @Autowired
    private RePortServiceImpl rePortMapperImpl;
    // 百分比
    @Autowired
    private UtilPortServiceImpl utilPortMapperImpl;
    // cpu
    @Autowired
    private CpuPortServicempl cpuPortMapperImpl;
    // 物理内存
    @Autowired
    private DramPorServiceImpl dramPortMapperImpl;
    // 闪存
    @Autowired
    private FlasPortServiceImpl flasPortMapperImpl;

    //告警
    @Autowired
    private CaveatServiceImpl caveatService;

    JSONObject sumJson = new JSONObject();

    /**
     * 传输帧速率端口流量数据
     *
     * @param json
     */
    @JmsListener(destination = "192.168.10.131")
    public void outPutPort131(String json) {
        tmTxt(Constants.SIP[0], json);
    }

    @JmsListener(destination = "192.168.10.51")
    public void outPutPort51(String json) {
        tmTxt(Constants.SIP[2], json);
    }

    @JmsListener(destination = "192.168.10.41")
    public void outPutPort41(String json) {
        tmTxt(Constants.SIP[1], json);
    }

    @JmsListener(destination = "192.168.10.31")
    public void outPutPort31(String json) {
        tmTxt(Constants.SIP[3], json);
    }

    @JmsListener(destination = "192.168.10.200")
    public void outPutPort200(String json) {
        tmTxt(Constants.SIP[4], json);
    }

    @JmsListener(destination = "192.168.10.201")
    public void outPutPort201(String json) {
        tmTxt(Constants.SIP[5], json);
    }

    /**
     * switch数据
     *
     * @param ip
     * @param json
     */
    public void tmTxt(String ip, String json) {
        List<Integer> portArr = null;
        int count = 0;
        // String 转换json
        sumJson = JSONObject.parseObject(json);
        // 通过ip获得conut 值 对应的存入数据库
        count = ip(ip);
        if (sumJson.containsKey("data")) {
            JSONObject dataJson = sumJson.getJSONObject("data");
            // tmPort解析获得对应的opList
            JSONArray opList = tmPort(dataJson);
            // rePort解析获得对应的reList
            JSONArray reList = rePort(dataJson);
            // utilPort解析获得对应的utilList
            JSONArray utilList = utilPort(dataJson);
            // cpuSwitch解析获得对应的cpuList
            JSONArray cpuList = cpuSwitch(dataJson);
            // radmSwitch解析radmList
            JSONArray dramList = radmSwitch(dataJson);
            // flasSwitch解析flasList
            JSONArray flasList = flasSwitch(dataJson);
            if (opList != null) {
                int total = saveTmPort(count, portArr, opList);
                if (total > 0) {
                    log.info(ip + ":saveTmPort:ok");
                } else
                    log.info(ip + ":saveTmPort:no");
            } else
                log.info(ip + ":opList:no");

            if (reList != null) {
                int total = saveRePort(count, portArr, reList);
                if (total > 0) {
                    log.info(ip + ":saveRePort:ok");
                } else
                    log.info(ip + ":saveRePort:no");

            } else
                log.info(ip + ":reList:no");

            if (utilList != null) {
                int total = saveUtilPort(count, portArr, utilList);
                if (total > 0) {
                    log.info(ip + ":saveUtilPort:ok");
                } else
                    log.info(ip + ":saveUtilPort:no");

            } else
                log.info(ip + ":utilList:no");
            if (cpuList != null) {
                int total = saveCpu(count, portArr, ip, cpuList);
                if (total > 0) {
                    log.info(ip + ":saveCpu:ok");
                } else
                    log.info(ip + ":saveCpu:no");
            } else
                log.info(ip + ":cpuList:no");
            if (dramList != null) {
                String saveDram = saveDram(ip + "Dram", count, portArr, dramList);
                if (StringUtils.isEmpty(saveDram)) {
                    log.info(ip + ":saveDram:null");
                } else if (saveDram.equals("ok")) {
                    log.info(ip + ":saveDram:ok");
                } else
                    log.info(ip + ":saveDram:no");
            }
            if (flasList != null) {
                String saveFlase = saveFlas(ip + "Flas", count, portArr, flasList);
                if (StringUtils.isEmpty(saveFlase)) {
                    log.info(ip + ":saveDram:null");
                } else if (saveFlase.equals("ok")) {
                    log.info(ip + ":saveFlas:ok");
                } else
                    log.info(ip + ":saveFlas:no");
            }
        } else {
            Caveat caveat = null;
            String content = ip + ": 交换机掉线";
            if (sumJson.containsKey("error")) {
                String errJson = sumJson.getString("error");
                if (errJson.equals("pingno")) {
                    caveat = new Caveat();
                    //设置告警内容
                    caveat.setWarncontent("交换机掉线");
                    //设置是否处理 1代表处理 0代表未处理'
                    caveat.setDealwith(0);
                    //设置属于哪个交换机的警告
                    caveat.setFromguy(ip);
                    //设置告警时间
                    caveat.setWarntime(new Date());
                    //设置哪个交换机的id
                    caveat.setSwitchId(count);
                    //设置告警级别：告警的严重程度 1一般  2 中等 3 严重
                    caveat.setWarnleave(3);
                    //存入数据库
                    caveatService.addCaveat(caveat);
                    //发送微信企业消息
                    sendCaveat(content);
                }
            }
        }
    }

    /**
     * 存入Flas数据
     *
     * @param count
     */
    public String saveFlas(String ip, int count, List<Integer> portNewArr, JSONArray flasList) {
        // 闪存对象
        Flas flas = null;
        // Flas内存信息
        if (flasList != null) {
            portNewArr = new ArrayList<Integer>();
            for (Object cp : flasList) {
                // 传输帧速率 转换 Integer
                portNewArr.add(Integer.parseInt(cp.toString()));
            }
            Long totalLongNew = Long.valueOf(portNewArr.get(1)); // total转换成Long
            Long usedLongNew = Long.valueOf(portNewArr.get(2));
            Integer utilIntegerNew = portNewArr.get(3);
            // 首先查询redis中有没有这个key
            String redisFlas = baseRedisService.getStirngKey(ip);
            if (StringUtils.isEmpty(redisFlas)) {
                // 查询获得ResponseBase对象
                ResponseBase findFlas = flasPortMapperImpl.findFlas(count);
                // 转换Dram 对象
                Flas dataFlas = (Flas) findFlas.getData();
                if (dataFlas == null) {
                    flas = new Flas(count, utilIntegerNew, totalLongNew, usedLongNew, new Date());
                    flasPortMapperImpl.addFlas(flas);
                    baseRedisService.setString(ip, flasList.toJSONString());
                    return "ok";
                }
            }
            // 如果redis里面有了
            List<Integer> portOldArr = new ArrayList<>();
            JSONArray redisArrayJson = JSONArray.parseArray(redisFlas);
            for (Object jsArr : redisArrayJson) {
                // oldFlas 转换 Integer
                portOldArr.add(Integer.parseInt(jsArr.toString()));
            }
            Long totalLongOld = Long.valueOf(portOldArr.get(1)); // total转换成Long
            Long usedLongOld = Long.valueOf(portOldArr.get(2));
            Integer utilIntegerOld = portOldArr.get(3);
            //如果一致则不进行任何操作
            if (totalLongOld.equals(totalLongNew) && usedLongOld.equals(usedLongNew)
                    && utilIntegerOld.equals(utilIntegerNew)) {
                // rt 等于redis里跟传过来的值一致
                return "rt";
            }
            //如果出现不一致
            flas = new Flas(count, utilIntegerNew, totalLongNew, usedLongNew, new Date());
            flasPortMapperImpl.addFlas(flas);
            //删除后
            baseRedisService.delData(ip);
            //重新录入
            baseRedisService.setString(ip, flasList.toJSONString());
            return "ok";
        }
        return null;
    }

    /**
     * 存入Dram数据
     *
     * @param count
     */
    public String saveDram(String ip, int count, List<Integer> portNewArr, JSONArray dramList) {
        // 物理内存端对象
        Dram dram = null;
        Dram dataDram = null;
        // Dram内存信息
        if (dramList != null) {
            portNewArr = new ArrayList<Integer>();
            for (Object cp : dramList) {
                // newDram 转换 Integer
                portNewArr.add(Integer.parseInt(cp.toString()));
            }
            Long totalLongNew = Long.valueOf(portNewArr.get(1)); // total转换成Long
            Long usedLongNew = Long.valueOf(portNewArr.get(2));
            Integer utilIntegerNew = portNewArr.get(3);
            // 首先查询redis中有没有这个key
            String redisDram = baseRedisService.getStirngKey(ip);
            if (StringUtils.isEmpty(redisDram)) {
                // 如果没有 去数据库查询有没有
                // 查询获得ResponseBase对象
                ResponseBase findDarm = dramPortMapperImpl.findDarm(count);
                // 转换Dram 对象
                dataDram = (Dram) findDarm.getData();

                // 如果数据库有了 我就返回
                if (dataDram == null) {
                    // 否则就新增数据库
                    dram = new Dram(count, utilIntegerNew, totalLongNew, usedLongNew, new Date());
                    dramPortMapperImpl.addDarm(dram);
                    baseRedisService.setString(ip, dramList.toJSONString());
                    return "ok";
                }
            }
            // 如果redis里面有了
            List<Integer> portOldArr = new ArrayList<>();
            JSONArray redisArrayJson = JSONArray.parseArray(redisDram);
            for (Object jsArr : redisArrayJson) {
                // oldDram 转换 Integer
                portOldArr.add(Integer.parseInt(jsArr.toString()));
            }
            Long totalLongOld = Long.valueOf(portOldArr.get(1)); // total转换成Long
            Long usedLongOld = Long.valueOf(portOldArr.get(2));
            Integer utilIntegerOld = portOldArr.get(3);
            //如果一致则不进行任何操作
            if (totalLongOld.equals(totalLongNew) && usedLongOld.equals(usedLongNew)
                    && utilIntegerOld.equals(utilIntegerNew)) {
                // rt 等于redis里跟传过来的值一致
                return "rt";
            }
            //如果出现不一致
            dram = new Dram(count, utilIntegerNew, totalLongNew, usedLongNew, new Date());
            dramPortMapperImpl.addDarm(dram);
            //删除后
            baseRedisService.delData(ip);
            //重新录入
            baseRedisService.setString(ip, dramList.toJSONString());
            return "ok";
        }
        return null;
    }

    /**
     * 存入CPU数据
     *
     * @param count
     * @param portArr
     * @param cpuList
     */
    public int saveCpu(int count, List<Integer> portArr, String ip, JSONArray cpuList) {
        portArr = new ArrayList<Integer>();
        Caveat caveat = null;
        List<Integer> finalPortArr = portArr;
        //Lambda表达式
        cpuList.forEach(x -> finalPortArr.add(Integer.parseInt(x.toString())));
        //判断cpu 的使用率
        String content = null;
        if (portArr.get(0) > 60 && portArr.get(0) <= 85 || portArr.get(0) > 85) {
            caveat = new Caveat();
            if (portArr.get(0) > 60 && portArr.get(0) <= 85) {
                caveat.setWarnleave(2);
                caveat.setWarncontent("CPU使用率过高");
                content = ip + ": CPU使用率过高 CPU当前使用率:" + portArr.get(0);
            } else if (portArr.get(0) > 85) {
                caveat.setWarnleave(3);
                caveat.setWarncontent("CPU使用率极高");
                content = ip + ": CPU使用率极高 CPU当前使用率:" + portArr.get(0);
            }

            caveat.setDealwith(0);
            caveat.setSwitchId(count);
            caveat.setFromguy(ip);
            caveat.setWarntime(new Date());
            caveatService.addCaveat(caveat);
            //发送企业微信运维推送
            sendCaveat(content);
        }
        if (count > 0) {
            // 新增数据
            cpuPortMapperImpl.addCpu(addCpuPort(portArr, count));
        }
        return count;
    }

    /**
     * 存入UtilPort数据
     *
     * @param count
     * @param portArr
     * @param utilList
     */
    public int saveUtilPort(int count, List<Integer> portArr, JSONArray utilList) {
        portArr = new ArrayList<Integer>();
        // 传输帧速率 转换
        List<Integer> finalPortArr = portArr;
        //Lambda 循环
        utilList.forEach(x -> finalPortArr.add(Integer.parseInt(x.toString())));
        if (count > 0) {
            addUtilPort(portArr, count);
            if (petPort instanceof UtilPort) {
                // 如果是的话强转存入数据库
                utilPortMapperImpl.addUtil((UtilPort) petPort);

            }
        }
        return count;
    }

    /**
     * 存入rePort数据
     *
     * @param count
     * @param portArr
     * @param reList
     */
    public int saveRePort(int count, List<Integer> portArr, JSONArray reList) {
        portArr = new ArrayList<Integer>();
        List<Integer> finalPortArr = portArr;
        //Lambda表达式
        reList.forEach(x -> finalPortArr.add(Integer.parseInt(x.toString())));
        if (count > 0) {
            addRePort(portArr, count);
            if (petPort instanceof ReceivePort) {
                // 如果是的话强转存入数据库
                rePortMapperImpl.addRe((ReceivePort) petPort);
            }
        }
        return count;
    }

    /**
     * 存入tmPort数据
     *
     * @param count
     * @param portArr
     * @param opList
     */
    public int saveTmPort(int count, List<Integer> portArr, JSONArray opList) {
        if (opList != null) {
            portArr = new ArrayList<Integer>();
            List<Integer> finalPortArr = portArr;
            //Lambda表达式
            opList.forEach(x -> finalPortArr.add(Integer.parseInt(x.toString())));
            if (count > 0) {
                addTmPort(portArr, count);
                if (petPort instanceof OutPutPort) {
                    // 如果是的话强转存入数据库
                    outPutPortMapperImpl.addOutPut((OutPutPort) petPort);
                }
            }

        }
        return count;
    }

    /**
     * 解析 FLAS
     */
    public JSONArray flasSwitch(JSONObject dataJson) {
        if (dataJson.containsKey("flasData")) {
            JSONObject flasObj = dataJson.getJSONObject("flasData");
            if (flasObj.containsKey("flasValue")) {
                return flasObj.getJSONArray("flasValue");
            }
        }
        return null;
    }

    /**
     * 解析 RADM
     */
    public JSONArray radmSwitch(JSONObject dataJson) {
        if (dataJson.containsKey("dramData")) {
            JSONObject radnObj = dataJson.getJSONObject("dramData");
            if (radnObj.containsKey("dramValue")) {
                return radnObj.getJSONArray("dramValue");
            }
        }
        return null;
    }

    /**
     * 解析Cpu
     *
     * @return
     */
    public JSONArray cpuSwitch(JSONObject dataJson) {
        if (dataJson.containsKey("cpuData")) {
            JSONObject opObj = dataJson.getJSONObject("cpuData");
            if (opObj.containsKey("cpuValue")) {
                return opObj.getJSONArray("cpuValue");
            }
        }
        return null;
    }

    /**
     * 解析rePort
     *
     * @return
     */
    public JSONArray rePort(JSONObject dataJson) {
        if (dataJson.containsKey("rePort")) {
            JSONObject reObj = dataJson.getJSONObject("rePort");
            if (reObj.containsKey("rePortValue")) {
                return reObj.getJSONArray("rePortValue");
            }
        }
        return null;
    }

    /**
     * 解析utilPort
     *
     * @return
     */
    public JSONArray utilPort(JSONObject dataJson) {
        if (dataJson.containsKey("pePort")) {
            JSONObject peObj = dataJson.getJSONObject("pePort");
            if (peObj.containsKey("pePortValue")) {
                return peObj.getJSONArray("pePortValue");
            }
        }

        return null;
    }

    /**
     * 解析tmPort
     */
    public JSONArray tmPort(JSONObject dataJson) {
        if (dataJson.containsKey("tmPort")) {
            JSONObject tmObj = dataJson.getJSONObject("tmPort");
            if (tmObj.containsKey("tmPortValue")) {
                return tmObj.getJSONArray("tmPortValue");
            }
        }
        return null;
    }

    /**
     * 判断长度存入对象
     *
     * @param count
     */
    public void addTmPort(List<Integer> opArr, int count) {
        if (opArr.size() == 24) {
            petPort = new OutPutPort(count, opArr.get(0), opArr.get(1), opArr.get(2), opArr.get(3), opArr.get(4),
                    opArr.get(5), opArr.get(6), opArr.get(7), opArr.get(8), opArr.get(9), opArr.get(10), opArr.get(11),
                    opArr.get(12), opArr.get(13), opArr.get(14), opArr.get(15), opArr.get(16), opArr.get(17),
                    opArr.get(18), opArr.get(19), opArr.get(20), opArr.get(21), opArr.get(22), opArr.get(23),
                    new Date());
        }
        if (opArr.size() == 26) {
            petPort = new OutPutPort(count, opArr.get(0), opArr.get(1), opArr.get(2), opArr.get(3), opArr.get(4),
                    opArr.get(5), opArr.get(6), opArr.get(7), opArr.get(8), opArr.get(9), opArr.get(10), opArr.get(11),
                    opArr.get(12), opArr.get(13), opArr.get(14), opArr.get(15), opArr.get(16), opArr.get(17),
                    opArr.get(18), opArr.get(19), opArr.get(20), opArr.get(21), opArr.get(22), opArr.get(23),
                    opArr.get(24), opArr.get(25), new Date());
        }
    }

    /**
     * 判断长度存入对象
     *
     * @param count
     */
    public void addRePort(List<Integer> opArr, int count) {
        if (opArr.size() == 24) {
            petPort = new ReceivePort(count, opArr.get(0), opArr.get(1), opArr.get(2), opArr.get(3), opArr.get(4),
                    opArr.get(5), opArr.get(6), opArr.get(7), opArr.get(8), opArr.get(9), opArr.get(10), opArr.get(11),
                    opArr.get(12), opArr.get(13), opArr.get(14), opArr.get(15), opArr.get(16), opArr.get(17),
                    opArr.get(18), opArr.get(19), opArr.get(20), opArr.get(21), opArr.get(22), opArr.get(23),
                    new Date());
        }
        if (opArr.size() == 26) {
            petPort = new ReceivePort(count, opArr.get(0), opArr.get(1), opArr.get(2), opArr.get(3), opArr.get(4),
                    opArr.get(5), opArr.get(6), opArr.get(7), opArr.get(8), opArr.get(9), opArr.get(10), opArr.get(11),
                    opArr.get(12), opArr.get(13), opArr.get(14), opArr.get(15), opArr.get(16), opArr.get(17),
                    opArr.get(18), opArr.get(19), opArr.get(20), opArr.get(21), opArr.get(22), opArr.get(23),
                    opArr.get(24), opArr.get(25), new Date());
        }
    }

    /**
     * 判断长度存入对象
     *
     * @param count
     */
    public void addUtilPort(List<Integer> opArr, int count) {
        if (opArr.size() == 24) {
            petPort = new UtilPort(count, opArr.get(0), opArr.get(1), opArr.get(2), opArr.get(3), opArr.get(4),
                    opArr.get(5), opArr.get(6), opArr.get(7), opArr.get(8), opArr.get(9), opArr.get(10), opArr.get(11),
                    opArr.get(12), opArr.get(13), opArr.get(14), opArr.get(15), opArr.get(16), opArr.get(17),
                    opArr.get(18), opArr.get(19), opArr.get(20), opArr.get(21), opArr.get(22), opArr.get(23),
                    new Date());
        }
        if (opArr.size() == 26) {
            petPort = new UtilPort(count, opArr.get(0), opArr.get(1), opArr.get(2), opArr.get(3), opArr.get(4),
                    opArr.get(5), opArr.get(6), opArr.get(7), opArr.get(8), opArr.get(9), opArr.get(10), opArr.get(11),
                    opArr.get(12), opArr.get(13), opArr.get(14), opArr.get(15), opArr.get(16), opArr.get(17),
                    opArr.get(18), opArr.get(19), opArr.get(20), opArr.get(21), opArr.get(22), opArr.get(23),
                    opArr.get(24), opArr.get(25), new Date());
        }
    }

    /**
     * 新增CPU数据
     *
     * @param opArr
     * @param count
     */
    public Cpu addCpuPort(List<Integer> opArr, int count) {
        return new Cpu(count, opArr.get(0), opArr.get(1), opArr.get(2), new Date());
    }

    /**
     * ip判断数据库对应的数 1 =51 2=41 3=31 4=131
     *
     * @param ip
     * @return
     */
    public int ip(String ip) {
        int ipCount = 0;
        if (ip.equals("192.168.10.51")) {
            return ipCount = 4;
        }
        if (ip.equals("192.168.10.41")) {
            return ipCount = 3;
        }
        if (ip.equals("192.168.10.31")) {
            return ipCount = 2;
        }
        if (ip.equals("192.168.10.131")) {
            return ipCount = 1;
        }
        if (ip.equals("192.168.10.200")) {
            return ipCount = 5;
        }
        if (ip.equals("192.168.10.201")) {
            return ipCount = 6;
        }
        return ipCount;
    }

    //企业微信告警发送方法 接口
    public void sendCaveat(String content) {
        JSONObject js = new JSONObject();
        //String result = null;
        for (int i = 0; i < Constants.CAVEAT.length; i++) {
            js.put("workWeixinId", Constants.CAVEAT[i]);
            js.put("content", content);
            js.put("agentid", "1000017");
            HttpUtils.doPost(Constants.POST_URL,js.toJSONString(),5000);
        }

    }
    public static void main(String [] args){
        JSONObject js = new JSONObject();
        js.put("workWeixinId","L0280");
        js.put("content", "测试");
        js.put("agentid", "1000017");
        HttpUtils.doPost(Constants.POST_URL,js.toJSONString(),5000);
    }
}
