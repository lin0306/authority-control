package com.lin.authoritycontrol.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.common.enums.keygen.KeygenUpdateFrequencyEnum;
import com.lin.authoritycontrol.common.exception.CustomException;
import com.lin.authoritycontrol.controller.keygen.form.KeygenSaveForm;
import com.lin.authoritycontrol.controller.keygen.form.KeygenUpdateForm;
import com.lin.authoritycontrol.controller.keygen.query.KeygenQuery;
import com.lin.authoritycontrol.controller.keygen.vo.KeygenVO;
import com.lin.authoritycontrol.mapper.KeygenConfigMapper;
import com.lin.authoritycontrol.mapper.domain.KeygenConfig;
import com.lin.authoritycontrol.service.KeygenConfigService;
import com.lin.authoritycontrol.service.keygen.KeygenGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 秘钥配置service实现
 *
 * @author 林维家
 * @since 2024/12/30 17:00
 */
@Service
public class KeygenConfigServiceImpl extends ServiceImpl<KeygenConfigMapper, KeygenConfig> implements KeygenConfigService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveKeygen(KeygenSaveForm form) {
        if (checkApiKeyExist(form.getApiKey(), null)) {
            throw new CustomException("秘钥编码已存在");
        }
        KeygenConfig config = new KeygenConfig(form);
        KeygenGenerator.generate(config, true);
        config.setLastGenerateTime(LocalDateTime.now());
        config.setCreatorId(""); // todo 暂时置空
        config.setCreateTime(LocalDateTime.now());

        save(config);
    }

    @Override
    public void updateKeygen(KeygenUpdateForm form) {
        if (checkApiKeyExist(form.getApiKey(), form.getApiId())) {
            throw new CustomException("秘钥编码已存在");
        }
        KeygenConfig config = new KeygenConfig(form);
        config.setModifierId(""); // todo 暂时置空
        config.setModifyTime(LocalDateTime.now());

        updateById(config);
    }

    @Override
    public PageVO<KeygenVO> getKeygenPage(KeygenQuery query) {
        IPage<KeygenConfig> page = lambdaQuery()
                .like(StrUtil.isNotBlank(query.getApiKey()), KeygenConfig::getApiKey, query.getApiKey())
                .like(StrUtil.isNotBlank(query.getApiName()), KeygenConfig::getApiName, query.getApiName())
                .eq(StrUtil.isNotBlank(query.getRelType()), KeygenConfig::getRelType, query.getRelType())
                .eq(StrUtil.isNotBlank(query.getKeyType()), KeygenConfig::getKeyType, query.getKeyType())
                .eq(query.getEnableFlag() != null, KeygenConfig::getEnableFlag, query.getEnableFlag())
                .page(new Page<>(query.getCurrent(), query.getSize()));
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return new PageVO<>(query);
        }
        PageVO<KeygenVO> pageVO = new PageVO<>(page);
        pageVO.setRecords(page.getRecords().stream().map(KeygenVO::new).collect(Collectors.toList()));
        return pageVO;
    }

    @Override
    public KeygenVO getKeygen(String apiId) {
        KeygenConfig config = getById(apiId);
        if (config.getDeleteFlag()) {
            return null;
        }
        return new KeygenVO(config);
    }

    @Override
    public String getPublicKey(String apiKey) {
        KeygenConfig config = getKeygenConfig(apiKey);
        return config == null ? null : config.getPublicKey();
    }

    @Override
    public void deleteKeygen(String apiId) {
        KeygenConfig config = getById(apiId);
        if (config == null) {
            return;
        }

        lambdaUpdate()
                .set(KeygenConfig::getDeleteFlag, true)
                .set(KeygenConfig::getModifierId, "") // todo 暂时置空
                .set(KeygenConfig::getModifyTime, LocalDateTime.now())
                .eq(KeygenConfig::getApiId, apiId)
                .update();
    }

    @Override
    public List<KeygenConfig> getUpdateKeygenList() {
        return lambdaQuery()
                .eq(KeygenConfig::getEnableFlag, true)
                .eq(KeygenConfig::getDeleteFlag, false)
                .ne(KeygenConfig::getUpdateFrequency, KeygenUpdateFrequencyEnum.PERMANENT.getCode())
                .le(KeygenConfig::getStartTime, LocalDateTime.now())
                .and(q -> q.isNull(KeygenConfig::getEndTime).or().ge(KeygenConfig::getEndTime, LocalDateTime.now()))
                .select(KeygenConfig::getApiId, KeygenConfig::getKeyType, KeygenConfig::getUpdateFrequency)
                .list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdate(List<KeygenConfig> list) {
        updateBatchById(list);
    }

    private KeygenConfig getKeygenConfig(String apiKey) {
        KeygenConfig config = lambdaQuery().eq(KeygenConfig::getApiKey, apiKey)
                .eq(KeygenConfig::getEnableFlag, true)
                .eq(KeygenConfig::getDeleteFlag, false)
                .one();
        if (config != null) {
            // 秘钥设置了开始时间，但是还没到开始时间，不允许获取
            if (config.getStartTime() != null && !config.getStartTime().isBefore(LocalDateTime.now())) {
                return null;
            }
            // 设置了过期时间，但是已经超过过期时间了，不允许获取
            if (config.getEndTime() != null && !config.getEndTime().isBefore(LocalDateTime.now())) {
                return null;
            }
        }
        return config;
    }

    private boolean checkApiKeyExist(String apiKey, String ignoreApiId) {
        return lambdaQuery()
                .eq(KeygenConfig::getApiKey, apiKey)
                .ne(StrUtil.isNotBlank(ignoreApiId), KeygenConfig::getApiId, ignoreApiId)
                .eq(KeygenConfig::getDeleteFlag, false)
                .select(KeygenConfig::getApiId)
                .exists();
    }
}
