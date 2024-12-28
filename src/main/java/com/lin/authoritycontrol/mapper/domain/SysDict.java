package com.lin.authoritycontrol.mapper.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 数据字典
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dict")
public class SysDict implements Serializable {
    /**
     * 唯一编码
     */
    @TableId(value = "item_id", type = IdType.ASSIGN_UUID)
    private String itemId;

    /**
     * 上级编码
     */
    @TableField(value = "p_id")
    private String pId;

    /**
     * 层级关系
     */
    @TableField(value = "parents")
    private String parents;

    /**
     * 字典code
     */
    @TableField(value = "item_code")
    private String itemCode;

    /**
     * 字典值
     */
    @TableField(value = "item_value")
    private String itemValue;

    /**
     * 标签
     */
    @TableField(value = "tags")
    private String tags;

    /**
     * 备注
     */
    @TableField(value = "memo")
    private String memo;

    /**
     * 创建人id
     */
    @TableField(value = "creator_id")
    private String creatorId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改人id
     */
    @TableField(value = "modifier_id")
    private String modifierId;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time")
    private LocalDateTime modifyTime;

    /**
     * 删除标记
     */
    @TableField(value = "delete_flag")
    private Boolean deleteFlag;

    private static final long serialVersionUID = 1L;
}