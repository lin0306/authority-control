package com.lin.authoritycontrol.cron;

import com.lin.authoritycontrol.common.enums.keygen.KeygenUpdateFrequencyEnum;
import com.lin.authoritycontrol.mapper.domain.KeygenConfig;
import com.lin.authoritycontrol.service.KeygenConfigService;
import com.lin.authoritycontrol.service.keygen.KeygenGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 秘钥更新定时任务
 *
 * @author 林维家
 * @since 2024/12/30 22:26
 */
@Slf4j
@Component
@AllArgsConstructor
public class KeygenUpdateSchedule {

    private final KeygenConfigService keygenConfigService;

    /**
     * 每天0点执行一次秘钥更新定时任务
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void schedule() {
        log.info("开始执行秘钥更新定时任务");
        StopWatch watch = new StopWatch();
        watch.start();
        List<KeygenConfig> list = keygenConfigService.getUpdateKeygenList();
        if (CollectionUtils.isEmpty(list)) {
            watch.stop();
            log.info("没有需要更新的秘钥");
            return;
        }
        List<KeygenConfig> toUpdateList = new ArrayList<>();
        list.forEach(config -> {
            KeygenUpdateFrequencyEnum typeEnum = KeygenUpdateFrequencyEnum.getByCode(config.getUpdateFrequency());
            if (typeEnum != null) {
                switch (typeEnum) {
                    case DAILY:
                        KeygenGenerator.generate(config, false);
                        toUpdateList.add(config);
                        break;
                    case WEEKLY:
                        // 每周一更新一次
                        if (LocalDateTime.now().getDayOfWeek().getValue() == 1) {
                            KeygenGenerator.generate(config, false);
                            toUpdateList.add(config);
                        }
                        break;
                    case MONTHLY:
                        // 每月更新一次
                        if (LocalDateTime.now().getDayOfMonth() == 1) {
                            KeygenGenerator.generate(config, false);
                            toUpdateList.add(config);
                        }
                        break;
                    case QUARTERLY:
                        // 每季度更新一次
                        if (LocalDateTime.now().getMonthValue() % 3 == 0) {
                            KeygenGenerator.generate(config, false);
                            toUpdateList.add(config);
                        }
                        break;
                    case HALF_YEARLY:
                        // 每半年更新一次
                        if (LocalDateTime.now().getMonthValue() % 6 == 0) {
                            KeygenGenerator.generate(config, false);
                            toUpdateList.add(config);
                        }
                        break;
                    case YEARLY:
                        // 每年更新一次
                        if (LocalDateTime.now().getMonthValue() == 1 && LocalDateTime.now().getDayOfMonth() == 1) {
                            KeygenGenerator.generate(config, false);
                            toUpdateList.add(config);
                        }
                        break;
                    default:
                }
            }
        });

        keygenConfigService.batchUpdate(toUpdateList);
        watch.stop();
        log.info("秘钥更新定时任务执行完成，更新数据条数：{}，耗时：{}ms", toUpdateList.size(), watch.getLastTaskTimeMillis());
    }
}