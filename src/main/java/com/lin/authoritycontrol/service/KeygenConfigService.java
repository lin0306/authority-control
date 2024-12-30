package com.lin.authoritycontrol.service;

import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.controller.keygen.form.KeygenSaveForm;
import com.lin.authoritycontrol.controller.keygen.form.KeygenUpdateForm;
import com.lin.authoritycontrol.controller.keygen.query.KeygenQuery;
import com.lin.authoritycontrol.controller.keygen.vo.KeygenVO;
import com.lin.authoritycontrol.mapper.domain.KeygenConfig;

import java.util.List;

/**
 * 秘钥配置service
 *
 * @author 林维家
 * @since 2024/12/30 17:00
 */
public interface KeygenConfigService {

    /**
     * 保存秘钥信息
     */
    void saveKeygen(KeygenSaveForm form);

    /**
     * 更新秘钥信息
     */
    void updateKeygen(KeygenUpdateForm form);

    /**
     * 查询秘钥信息列表
     */
    PageVO<KeygenVO> getKeygenPage(KeygenQuery query);

    /**
     * 获取秘钥详情
     */
    KeygenVO getKeygen(String apiId);

    /**
     * 获取公钥
     */
    String getPublicKey(String apiKey);

    /**
     * 删除秘钥
     */
    void deleteKeygen(String apiId);

    /**
     * 获取可更新的秘钥列表
     */
    List<KeygenConfig> getUpdateKeygenList();

    /**
     * 批量更新秘钥
     */
    void batchUpdate(List<KeygenConfig> list);
}
