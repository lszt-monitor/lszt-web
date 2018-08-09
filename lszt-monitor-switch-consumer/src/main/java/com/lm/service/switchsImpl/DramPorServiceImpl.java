package com.lm.service.switchsImpl;

import com.github.pagehelper.PageHelper;
import com.lm.api.model.darm.Dram;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.switchs.DramPortMapper;
import com.lm.service.swtich.SwitchDarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DramPorServiceImpl extends BaseApiService implements SwitchDarmService {
	@Autowired
	private DramPortMapper dramPortMapper;

	/**
	 * dram
	 */
	@Override
	public ResponseBase addDarm(@RequestBody Dram dram) {
		int saveDram = dramPortMapper.saveDram(dram);
		if (saveDram < 0) {
			return setResultSuccess("no");
		}
		return setResultSuccess(saveDram);
	}

	@Override
	public ResponseBase findDarm(@RequestBody int id_dram) {
		PageHelper.startPage(0, 1);
		Dram findByDramTime = dramPortMapper.findByDramTime(id_dram);
		if (findByDramTime == null) {
			return setResultError("null");
		}
		return setResultSuccess(findByDramTime);
	}
}
