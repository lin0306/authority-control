package com.lin.authoritycontrol.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.controller.sys.user.form.UserForm;
import com.lin.authoritycontrol.controller.sys.user.query.UserQuery;
import com.lin.authoritycontrol.controller.sys.user.vo.UserVO;
import com.lin.authoritycontrol.mapper.SysUserMapper;
import com.lin.authoritycontrol.mapper.domain.SysUserDO;
import com.lin.authoritycontrol.service.SysUserService;
import com.lin.authoritycontrol.util.AESUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户service实现
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserForm user) {
        SysUserDO sysUser = new SysUserDO();
        sysUser.setUserName(AESUtil.encrypt(user.getUserName()));
        sysUser.setUserNameHash(AESUtil.hash(user.getUserName()));
        sysUser.setPassword(passwordEncoder.encode(user.getPassword()));
        save(sysUser);
    }

    @Override
    public List<UserVO> getUserList(UserQuery query) {
        log.info("userName: {}", query.getUserName());
        List<SysUserDO> doList = lambdaQuery()
                .like(StrUtil.isNotBlank(query.getUserName()), SysUserDO::getUserNameHash, AESUtil.hash(query.getUserName()))
                .list();

        return doList.stream().map(UserVO::new).collect(Collectors.toList());
    }
}
