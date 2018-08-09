package com.lm.service.switchsImpl;

import com.lm.api.model.cpu.Cpu;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.switchs.CpuPortMapper;
import com.lm.service.swtich.SwitchCpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CpuPortServicempl extends BaseApiService implements SwitchCpuService {
	@Autowired
	private CpuPortMapper cpuPortMapper;

	/**
	 * cpu使用率
	 */
	@Override
	public ResponseBase addCpu(@RequestBody Cpu cpu) {
		int saveCpu = cpuPortMapper.saveCpu(cpu);
		if (saveCpu < 0) {
			return setResultSuccess("no");
		}
		return setResultSuccess(saveCpu);
	}

}
