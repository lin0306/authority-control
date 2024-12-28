package com.lin.authoritycontrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.mapper.LoginLogMapper;
import com.lin.authoritycontrol.mapper.domain.LoginLog;
import com.lin.authoritycontrol.service.LoginLogService;
import org.springframework.stereotype.Service;

/**
 * 登录日志service实现
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
