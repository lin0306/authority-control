package com.lin.authoritycontrol.mapper.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lin.authoritycontrol.controller.sys.user.form.UserSaveForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserUpdateForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户个人信息
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_profile")
public class UserProfile implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "u_id", type = IdType.INPUT)
    private String uId;

    /**
     * 姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 性别 0-女，1-男
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 身份卡
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 身份卡hash值，用于模糊查询
     */
    @TableField(value = "id_card_hash")
    private String idCardHash;

    /**
     * 身份卡类型
     */
    @TableField(value = "id_card_type")
    private String idCardType;

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

    public UserProfile(String userId, UserSaveForm user) {
        this.uId = userId;
        this.realName = user.getRealName();
        this.gender = user.getGender();
        this.idCardType = user.getIdCardType();
    }

    public UserProfile(String userId, UserUpdateForm user) {
        this.uId = userId;
        this.realName = user.getRealName();
        this.gender = user.getGender();
        this.idCardType = user.getIdCardType();
    }
}