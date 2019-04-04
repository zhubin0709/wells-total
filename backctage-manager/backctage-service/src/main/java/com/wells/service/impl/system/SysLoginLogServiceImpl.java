package com.wells.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.wells.mapper.SysLoginLogMapper;
import com.wells.pojo.SysLoginLog;
import com.wells.service.backctage.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zb on 2019/1/26
 */
@Service
@Component
public class SysLoginLogServiceImpl implements SysLoginLogService {
    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;
    @Override
    public int insertLoginLog(SysLoginLog sysLoginLog) {
        return sysLoginLogMapper.insert(sysLoginLog);
    }
}
