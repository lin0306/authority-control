package com.lin.authoritycontrol.common.base;

import lombok.Setter;

import java.util.Optional;

/**
 * 分页查询
 *
 * @author 林维家
 * @since 2024/12/25 下午9:12
 */
@Setter
public class PageForm {

    /**
     * 页码
     */
    private Long current;

    /**
     * 每页条数
     */
    private Long size;

    public Long getCurrent() {
        return Optional.ofNullable(current).orElse(1L);
    }

    public Long getSize() {
        return Optional.ofNullable(size).orElse(10L);
    }

}
