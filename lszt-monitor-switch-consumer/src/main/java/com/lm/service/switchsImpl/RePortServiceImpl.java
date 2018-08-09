package com.lm.service.switchsImpl;

import com.lm.api.model.port.ReceivePort;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.switchs.ReceivePortMapper;
import com.lm.service.swtich.SwitchReService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RePortServiceImpl extends BaseApiService implements SwitchReService {

	@Autowired
	private ReceivePortMapper receivePortMapper;

	/**
	 * 新增接收帧速率
	 */
	@Override
	public ResponseBase addRe(@RequestBody ReceivePort re) {
		int saveReceive = receivePortMapper.saveReceive(re);
		if (saveReceive < 0) {
			return setResultSuccess("no");
		}
		return setResultSuccess(saveReceive);
	}

}
