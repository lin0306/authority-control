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

/**
 * 用户
 *
 * @author 林维家
 * @since 2024/12/28 下午4:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    /**
     * 唯一编码
     */
    @TableId(value = "u_id", type = IdType.ASSIGN_UUID)
    private String uId;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户名hash值，用于模糊搜索
     */
    @TableField(value = "user_name_hash")
    private String userNameHash;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

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