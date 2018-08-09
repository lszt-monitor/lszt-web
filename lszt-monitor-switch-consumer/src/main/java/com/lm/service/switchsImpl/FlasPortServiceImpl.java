package com.lm.service.switchsImpl;

import com.github.pagehelper.PageHelper;
import com.lm.api.model.flas.Flas;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.switchs.FlasPortMapper;
import com.lm.service.swtich.SwitchFlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class FlasPortServiceImpl extends BaseApiService implements SwitchFlasService {
	@Autowired
	private FlasPortMapper flasPortMapper;

	/**
	 * dram
	 */
	@Override
	public ResponseBase addFlas(@RequestBody Flas flas) {
		int saveFlas = flasPortMapper.saveFlas(flas);
		if (saveFlas < 0) {
			return setResultSuccess("no");
		}
		return setResultSuccess(saveFlas);
	}
	
	@Override
	public ResponseBase findFlas(@RequestBody int id_flas) {
		PageHelper.startPage(0, 1);
		Flas findByFlasTime = flasPortMapper.findByFlasTime(id_flas);
		if (findByFlasTime == null) {
			return setResultError("null");
		}
		return setResultSuccess(findByFlasTime);
	}

}
