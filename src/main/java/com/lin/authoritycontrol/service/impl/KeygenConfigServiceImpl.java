package com.lin.authoritycontrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.mapper.KeygenConfigMapper;
import com.lin.authoritycontrol.mapper.domain.KeygenConfig;
import com.lin.authoritycontrol.service.KeygenConfigService;
import org.springframework.stereotype.Service;

/**
 * 秘钥配置service实现
 *
 * @author 林维家
 * @since 2024/12/30 17:00
 */
@Service
public class KeygenConfigServiceImpl extends ServiceImpl<KeygenConfigMapper, KeygenConfig> implements KeygenConfigService {

}
