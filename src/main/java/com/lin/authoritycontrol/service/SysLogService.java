package com.lin.authoritycontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.authoritycontrol.common.enums.log.LogRelTypeEnum;
import com.lin.authoritycontrol.common.enums.log.LogTypeEnum;
import com.lin.authoritycontrol.mapper.domain.SysLog;

/**
 * 登录日志
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 添加日志
     */
    void addLog(LogTypeEnum type, String relId, LogRelTypeEnum relType, String describe);
}
