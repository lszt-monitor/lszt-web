package com.lm.service.switchsImpl;

import com.lm.api.model.port.OutPutPort;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.switchs.OutPutPortMapper;
import com.lm.service.swtich.SwitchOutPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OutPutPortServiceImpl extends BaseApiService implements SwitchOutPutService {
	
	@Autowired
	private OutPutPortMapper outPutPortMapper;
	/**
	 * 插入输出数据接口
	 */
	@Override
	public ResponseBase addOutPut(@RequestBody OutPutPort op) {
		int saveOutPut = outPutPortMapper.saveOutPut(op);
		if (saveOutPut < 0) {
			return setResultSuccess("no");
		}
		return setResultSuccess(saveOutPut);
	}

}
