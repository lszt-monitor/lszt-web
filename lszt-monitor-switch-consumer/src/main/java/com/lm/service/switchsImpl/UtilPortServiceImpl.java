package com.lm.service.switchsImpl;

import com.lm.api.model.port.UtilPort;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.switchs.UtilPortMapper;
import com.lm.service.swtich.SwitchUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UtilPortServiceImpl extends BaseApiService implements SwitchUtilService {

	@Autowired
	private UtilPortMapper utilPortMapper;

	/**
	 * 速率百分比
	 */
	@Override
	public ResponseBase addUtil(@RequestBody UtilPort util) {
		int saveUtil = utilPortMapper.saveUtil(util);
		if (saveUtil < 0) {
			return setResultSuccess("no");
		}
		return setResultSuccess(saveUtil);
	}

}
