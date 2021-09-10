package xyz.yurihentai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
// 配置注解后未生效，写配置文件正常拦截  原因在于将Bean对象的管理分别交给了 Spring 和 SpringMVC两个不同的Spring容器管理  Configuration在Spring容器中，无法影响到SpringMVC的容器中的Controller对象
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(DataSource dataSource,
                          UserDetailsService userDetailsService) {
        super();
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.formLogin().loginPage("/login"); // 指定自定义登录界面

//        http.csrf().disable(); // 禁用csrf（默认会要求登录时带上csrfToken进行验证（"rX-CSRF-TOKEN"请求头，或"_csrf"参数） 防止跨站请求伪造

        http.authorizeRequests()
                .antMatchers("/views/index.*").permitAll() //设置 放行所有 匹配的资源(可变参数)
                // 基于角色的访问控制
//                .antMatchers("/test/Yuri/**").hasRole("Yuri") // 设置指定路径下的资源 必须拥有指定的 角色 才能访问
//                .antMatchers("/test/YuriAdmin/**").hasAnyRole("Yuri","Admin") // 拥有指定的 任一角色 就能能访问
                // 基于权限的访问控制
//                .antMatchers("/test/delete/**").hasAuthority("Update") // 拥有指定 权限 才能访问
//                .antMatchers("/test/select/**").hasAnyAuthority("Update","Select","Delete") //用有指定的 任一权限 就能访问
                // 只允许完整认证的用户访问（非remember-me）
//                .antMatchers("/test/dangerous/**").fullyAuthenticated()
                // 写在最后 剩余任何资源 必须认证
                .anyRequest().authenticated();

        // 框架自带 登录状态数据库持久化 实现方式
//        JdbcTokenRepositoryImpl jtp = new JdbcTokenRepositoryImpl();
//        jtp.setDataSource(dataSource);
        //jtp.setCreateTableOnStartup(true);  //用于初次使用时自动创建数据表
        http.rememberMe();   // 记住登录状态  默认存在内存中，重启服务器失效  默认有效期为两周（org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices
//                .tokenRepository(jtp);  // 登录状态持久化
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        // 内存用户认证 写死在代码里
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder(6 + 9);
        auth.inMemoryAuthentication()
                .passwordEncoder(bCryptEncoder) // 设置密码加密方式
                .withUser("admin").password(bCryptEncoder.encode("admin")).roles("ADMIN")
                .and().withUser("simple").password(bCryptEncoder.encode("naive")).roles("USER");
        */

        // 自定义用户认证
        auth.userDetailsService(userDetailsService)
                // 可以实现PasswordEncoder接口自定义一个
                // 登录时可能抛异常,确保数据库中的密码密文加密方式与此处设置的相同 java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
                .passwordEncoder(new BCryptPasswordEncoder(6 + 9));
    }

    //高版本的Spring-Security对url的校验比较严格  对url中出现“/test//b”等不规范情况会抛以下异常  重载此方法进行配置，解除此限制
    //org.springframework.security.web.firewall.RequestRejectedException: The request was rejected because the URL was not normalized.
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.httpFirewall(new DefaultHttpFirewall());
    }

}
