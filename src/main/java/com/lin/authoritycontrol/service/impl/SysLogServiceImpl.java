package com.lin.authoritycontrol.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.common.enums.log.LogRelTypeEnum;
import com.lin.authoritycontrol.common.enums.log.LogTypeEnum;
import com.lin.authoritycontrol.mapper.SysLogMapper;
import com.lin.authoritycontrol.mapper.domain.SysLog;
import com.lin.authoritycontrol.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 系统日志service实现
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Slf4j
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public void addLog(LogTypeEnum type, String relId, LogRelTypeEnum relType, String describe) {
        SysLog sysLog = new SysLog();
        sysLog.setType(type.getCode());
        sysLog.setRelId(relId);
        sysLog.setRelType(relType.getCode());
        sysLog.setDescribe(describe);
        sysLog.setCreatorId(""); // todo 暂时置空
        sysLog.setCreateTime(LocalDateTime.now());
        try {
            save(sysLog);
        } catch (Exception e) {
            log.error("添加系统日志失败, logInfo: " + JSONUtil.toJsonStr(sysLog), e);
        }
    }
}
