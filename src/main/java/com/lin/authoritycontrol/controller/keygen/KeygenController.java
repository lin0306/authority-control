package com.lin.authoritycontrol.controller.keygen;

import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.common.base.Result;
import com.lin.authoritycontrol.controller.keygen.form.KeygenSaveForm;
import com.lin.authoritycontrol.controller.keygen.form.KeygenUpdateForm;
import com.lin.authoritycontrol.controller.keygen.query.KeygenQuery;
import com.lin.authoritycontrol.controller.keygen.vo.KeygenVO;
import com.lin.authoritycontrol.service.KeygenConfigService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 秘钥管理
 *
 * @author 林维家
 * @since 2024/12/30 20:42
 */
@RestController
@AllArgsConstructor
@RequestMapping("keygen")
public class KeygenController {

    private final KeygenConfigService keygenConfigService;

    /**
     * 保存秘钥
     */
    @PostMapping
    public Result<Void> saveKeygen(@RequestBody KeygenSaveForm form) {
        keygenConfigService.saveKeygen(form);
        return Result.success();
    }

    /**
     * 修改秘钥
     */
    @PutMapping("{apiId}")
    public Result<Void> updateKeygen(@PathVariable String apiId, @RequestBody KeygenUpdateForm form) {
        form.setApiId(apiId);
        keygenConfigService.updateKeygen(form);
        return Result.success();
    }

    /**
     * 获取秘钥列表
     */
    @GetMapping
    public Result<PageVO<KeygenVO>> getKeygenPage(KeygenQuery query) {
        return Result.success(keygenConfigService.getKeygenPage(query));
    }

    /**
     * 获取秘钥
     */
    @GetMapping("{apiId}")
    public Result<KeygenVO> getKeygen(@PathVariable String apiId) {
        return Result.success(keygenConfigService.getKeygen(apiId));
    }

    /**
     * 获取公钥
     */
    @GetMapping("{apiKey}/pk")
    public Result<String> getPublicKey(@PathVariable String apiKey) {
        return Result.success(keygenConfigService.getPublicKey(apiKey));
    }

    @DeleteMapping("{apiId}")
    public Result<Void> deleteKeygen(@PathVariable String apiId) {
        keygenConfigService.deleteKeygen(apiId);
        return Result.success();
    }
}
