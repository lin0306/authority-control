package com.lin.authoritycontrol.controller.keygen;

import com.lin.authoritycontrol.RespCheck;
import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.common.base.Result;
import com.lin.authoritycontrol.common.enums.keygen.KeygenRelTypeEnum;
import com.lin.authoritycontrol.common.enums.keygen.KeygenTypeEnum;
import com.lin.authoritycontrol.common.enums.keygen.KeygenUpdateFrequencyEnum;
import com.lin.authoritycontrol.controller.keygen.form.KeygenSaveForm;
import com.lin.authoritycontrol.controller.keygen.form.KeygenUpdateForm;
import com.lin.authoritycontrol.controller.keygen.query.KeygenQuery;
import com.lin.authoritycontrol.controller.keygen.vo.KeygenVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author 林维家
 * @since 2024/12/30 22:12
 */
@SpringBootTest
class KeygenControllerTest {

    @Resource
    private KeygenController keygenController;

    @Transactional
    @Test
    void test() {
        KeygenSaveForm saveForm = new KeygenSaveForm();
        saveForm.setApiKey("A001");
        saveForm.setApiName("测试秘钥");
        saveForm.setDescribe("测试秘钥");
        saveForm.setRelType(KeygenRelTypeEnum.LOGIN_KEY.getCode());
        saveForm.setKeyType(KeygenTypeEnum.ECDH.getCode());
        saveForm.setTags("测试秘钥");
        saveForm.setUpdateFrequency(KeygenUpdateFrequencyEnum.DAILY.getCode());
        saveForm.setStartTime(LocalDateTime.now());
        saveForm.setEndTime(null);
        saveForm.setEnableFlag(true);
        Result<Void> result = keygenController.saveKeygen(saveForm);
        RespCheck.checkCode(result, true);

        KeygenQuery query = new KeygenQuery();
        Result<PageVO<KeygenVO>> result1 = keygenController.getKeygenPage(query);
        RespCheck.checkData(result1, true);

        String apiId = result1.getData().getRecords().get(0).getApiId();

        query.setApiKey("A001");
        query.setApiName("测试秘钥");
        query.setRelType(KeygenRelTypeEnum.LOGIN_KEY.getCode());
        query.setKeyType(KeygenTypeEnum.ECDH.getCode());
        query.setEnableFlag(true);
        result1 = keygenController.getKeygenPage(query);
        RespCheck.checkData(result1, true);

        KeygenUpdateForm updateForm = new KeygenUpdateForm();
        updateForm.setApiKey("A002");
        updateForm.setApiName("测试秘钥1");
        updateForm.setDescribe("测试秘钥1");
        updateForm.setRelType(KeygenRelTypeEnum.LOGIN_KEY.getCode());
        updateForm.setTags("测试秘钥1");
        updateForm.setUpdateFrequency(KeygenUpdateFrequencyEnum.MONTHLY.getCode());
        updateForm.setStartTime(LocalDateTime.now());
        updateForm.setEndTime(null);
        updateForm.setEnableFlag(true);
        Result<Void> result2 = keygenController.updateKeygen(apiId, updateForm);
        RespCheck.checkCode(result2, true);

        Result<KeygenVO> result3 = keygenController.getKeygen(apiId);
        RespCheck.checkData(result3, true);

        Result<String> result4 = keygenController.getPublicKey(result3.getData().getApiKey());
        RespCheck.checkData(result4, true);

        Result<Void> result5 = keygenController.deleteKeygen(apiId);
        RespCheck.checkCode(result5, true);

        result3 = keygenController.getKeygen(apiId);
        RespCheck.checkData(result3, false);
    }

}