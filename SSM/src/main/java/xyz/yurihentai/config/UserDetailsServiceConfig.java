package xyz.yurihentai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.yurihentai.bean.权限;
import xyz.yurihentai.bean.用户;
import xyz.yurihentai.bean.角色;
import xyz.yurihentai.service.用户服务;

import java.util.ArrayList;
import java.util.List;

@Component("userDetailsService")
public class UserDetailsServiceConfig implements UserDetailsService {

    private final 用户服务 用户服务;

    public static final String DEFAULT_ROLE_PREFIX = "ROLE_";

    @Autowired
    public UserDetailsServiceConfig(用户服务 用户服务) {
        this.用户服务 = 用户服务;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        用户 用户 = 用户服务.通过账号查找用户(account);
        if(用户 == null) {
            throw new UsernameNotFoundException("账号不存在！");
        }
       List<String> 授权List = new ArrayList<>();
        List<角色> 角色List = 用户服务.通过用户编号查找用户角色(用户.get编号());
        List<权限> 权限List = new ArrayList<>();
        for (角色 角色 : 角色List) {
            授权List.add(DEFAULT_ROLE_PREFIX + 角色.get名称());
            权限List.addAll(用户服务.通过角色编号查找权限(角色.get编号()));
        }
        for (权限 权限 : 权限List) {
            授权List.add(权限.get名称());
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(授权List.toArray(new String[]{}));

        return new User(用户.get账号(), 用户.get密码(), authorityList);
    }

}
