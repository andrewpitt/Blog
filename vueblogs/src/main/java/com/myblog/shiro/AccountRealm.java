package com.myblog.shiro;

import com.myblog.entity.User;
import com.myblog.service.UserService;
import com.myblog.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权,根据已认证的安全数据获取到用户的权限信息
    //principalCollection包含所有已认证的安全数据
    //AuthorizationInfo 返回值是授权数据，即根据用户信息从数据库查出用户的权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //登录认证，比较用户名和密码与数据库是否一致，一致将安全数据（我们存的是自定义的profile）存入到shiro进行保管
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;

        String userId = jwtUtils.getClaimByToken((String)jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        if (user.getStatus() == -1) {
            throw new LockedAccountException("用户已被锁定！");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user, profile);
        //成功后向shiro存入安全数据
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
