package com.lm.service.switchsImpl;

import com.lm.api.model.Caveat;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.switchs.CaveatMapper;
import com.lm.service.swtich.SwitchLCaveatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaveatServiceImpl extends BaseApiService implements SwitchLCaveatService {
    @Autowired
    private CaveatMapper caveatMapper;

    /**
     * 告警设置
     */
    @Override
    public ResponseBase addCaveat(Caveat caveat){
        int saveCaveat = caveatMapper.saveCall(caveat);
        if (saveCaveat < 0) {
            return setResultSuccess("no");
        }
        return setResultSuccess(saveCaveat);
    }
}
