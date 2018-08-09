package com.lm.windows.scheduled;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lm.mp.RegisterMailboxProducer;
import com.lm.toos.Constants;
import com.lm.windows.readinformationproperties.WindowsReadinformationPropertiesGet;

@Component
public class WindowsScheduled {
	// mq 消息队列

	@Autowired
	private RegisterMailboxProducer mqMsg;

	@Autowired
	private WindowsReadinformationPropertiesGet wdinGet;

	/**
	 * 获取146信息
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws JMSException
	 */
	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows146() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[0], new JSONObject()).get();
			mqMsg.senMsg(cp, Constants.WIP[0]);
	}

	/**
	 * 获取32信息
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws JMSException
	 */
	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows32() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[1], new JSONObject()).get();
			mqMsg.senMsg(cp, Constants.WIP[1]);
	}

	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows137() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[2], new JSONObject()).get();
			mqMsg.senMsg(cp, Constants.WIP[2]);
	}

	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows107() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[3], new JSONObject()).get();
			mqMsg.senMsg(cp, Constants.WIP[3]);
	}

	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows228() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[4], new JSONObject()).get();
			mqMsg.senMsg(cp, Constants.WIP[4]);
	}

	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows170() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[5], new JSONObject()).get();
			mqMsg.senMsg(cp, Constants.WIP[5]);
	}

	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows218() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[6], new JSONObject()).get();
			mqMsg.senMsg(cp, Constants.WIP[6]);
	}

	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows2() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[7], new JSONObject()).get();
		mqMsg.senMsg(cp, Constants.WIP[7]);
	}

	//@Scheduled(fixedRate = Constants.WINDOWSSIZE)
	@Async("executor")
	public void proWindows156() throws IOException, InterruptedException, ExecutionException, JMSException {
		String cp = wdinGet.windowsGet(Constants.WIP[8], new JSONObject()).get();
			mqMsg.senMsg(cp, Constants.WIP[8]);
	}
}
