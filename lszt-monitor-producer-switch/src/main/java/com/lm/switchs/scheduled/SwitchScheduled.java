package com.lm.switchs.scheduled;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.jms.JMSException;

import com.lm.utils.PingUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.lm.base.BaseApiService;
import com.lm.mp.RegisterMailboxProducer;
import com.lm.switchs.readinformationproperties.SwitchReadinformationProperties;
import com.lm.toos.Constants;

@Component
public class SwitchScheduled extends BaseApiService {
    // mq 消息队列
    @Autowired
    private RegisterMailboxProducer mqMsg;

    @Autowired
    private SwitchReadinformationProperties swGet;

    /**
     * 生产消息131
     *
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JMSException
     */
    @Scheduled(cron = Constants.SNMPSIZE)
    @Async("executor")
    public void proSwitch131() throws InterruptedException, ExecutionException {
        // 131
        boolean pingFlg = PingUtils.ping(Constants.SIP[0], 5, 10);
        String walkGet = swGet.switchGet(pingFlg, Constants.SIP[0]).get();
        if (StringUtils.isNotEmpty(walkGet)) {
            mqMsg.senMsg(walkGet, Constants.SIP[0]);
        }

    }

    /**
     * 生产消息41
     *
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JMSException
     */
    @Scheduled(cron = Constants.SNMPSIZE)
    @Async("executor")
    public void proSwitch41() throws InterruptedException, ExecutionException {
        // 41
        boolean pingFlg = PingUtils.ping(Constants.SIP[1], 5, 10);
        String walkGet = swGet.switchGet(pingFlg,Constants.SIP[1]).get();
        if (StringUtils.isNotEmpty(walkGet)) {
            mqMsg.senMsg(walkGet, Constants.SIP[1]);
        }
    }

    /**
     * 生产消息51
     *
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JMSException
     */
    @Scheduled(cron = Constants.SNMPSIZE)
    @Async("executor")
    public void proSwitch51() throws InterruptedException, ExecutionException {
        // 51
        boolean pingFlg = PingUtils.ping(Constants.SIP[2], 5, 10);
        String walkGet = swGet.switchGet(pingFlg,Constants.SIP[2]).get();
        if (StringUtils.isNotEmpty(walkGet)) {
            mqMsg.senMsg(walkGet, Constants.SIP[2]);
        }
    }

    /**
     * 生产消息31
     *
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JMSException
     */
    @Scheduled(cron = Constants.SNMPSIZE)
    @Async("executor")
    public void proSwitch31() throws InterruptedException, ExecutionException {
        // 31
        boolean pingFlg = PingUtils.ping(Constants.SIP[3], 5, 10);
        String walkGet = swGet.switchGet(pingFlg,Constants.SIP[3]).get();
        if (StringUtils.isNotEmpty(walkGet)) {
            mqMsg.senMsg(walkGet, Constants.SIP[3]);
        }
    }

    /**
     * 生产消息200
     *
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JMSException
     */
    @Scheduled(cron = Constants.SNMPSIZE)
    @Async("executor")
    public void proSwitch200() throws InterruptedException, ExecutionException {
        boolean pingFlg = PingUtils.ping(Constants.SIP[4], 5, 10);
        String walkGet = swGet.switchGet(pingFlg,Constants.SIP[4]).get();
        if (StringUtils.isNotEmpty(walkGet)) {
            mqMsg.senMsg(walkGet, Constants.SIP[4]);
        }
    }

    /**
     * 生产消息201
     *
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JMSException
     */
    @Scheduled(cron = Constants.SNMPSIZE)
    @Async("executor")
    public void proSwitch201() throws InterruptedException, ExecutionException {
        boolean pingFlg = PingUtils.ping(Constants.SIP[5], 5, 10);
        String walkGet = swGet.switchGet(pingFlg,Constants.SIP[5]).get();
        if (StringUtils.isNotEmpty(walkGet)) {
            mqMsg.senMsg(walkGet, Constants.SIP[5]);
        }
    }
}
