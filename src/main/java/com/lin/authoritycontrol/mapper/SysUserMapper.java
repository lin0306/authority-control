package com.lin.authoritycontrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.authoritycontrol.controller.sys.user.query.UserQuery;
import com.lin.authoritycontrol.controller.sys.user.vo.UserVO;
import com.lin.authoritycontrol.mapper.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 *
 * @author 林维家
 * @since 2024/12/28 下午4:29
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 分页查询用户
     *
     * @param objectPage 分页对象
     * @param query      查询条件
     * @return 用户列表
     */
    Page<UserVO> queryUserPage(Page<UserVO> objectPage, @Param("query") UserQuery query);
}