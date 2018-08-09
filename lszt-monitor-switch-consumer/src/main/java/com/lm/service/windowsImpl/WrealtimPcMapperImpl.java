package com.lm.service.windowsImpl;

import com.lm.api.model.windows.WindowsPc;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.windows.WrealtimPcMapper;
import com.lm.service.windows.WrealtimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WrealtimPcMapperImpl extends BaseApiService implements WrealtimService {
	@Autowired
	private WrealtimPcMapper wrealtimMapper;

	@Override
	public ResponseBase save(WindowsPc windows) {
		int saveWd = wrealtimMapper.saveWd(windows);
		if (saveWd < 0) {
			return setResultError("no");
		}
		return setResultSuccess(saveWd);
	}

}
