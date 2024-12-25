package com.lin.authoritycontrol.common.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息
 *
 * @author 林维家
 * @since 2024/12/25 下午9:12
 */
@Getter
@Setter
public class PageVO<T> {

    private Long current;

    private Long size;

    private Long total;

    private List<T> records;

    public PageVO() {
    }

    public PageVO(Long total, Long current, List<T> records) {
        this.total = total;
        this.current = current;
        this.records = records;
    }

    /**
     * 没有查到任何数据调用这个构造函数直接返回
     */
    public PageVO(PageForm query) {
        this.current = query.getCurrent();
        this.size = query.getSize();
        this.total = 0L;
        this.records = new ArrayList<>();
    }

    /**
     * IPage的泛型和 BasePage的泛型不一致，这里records不保留数据
     */
    public <S> PageVO(IPage<S> page) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.records = new ArrayList<>(page.getRecords().size());
    }

    /**
     * Page的泛型和 BasePage的泛型一致，这里records直接用page.records的数据
     */
    public PageVO(Page<T> page) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.records = page.getRecords();
    }
}
