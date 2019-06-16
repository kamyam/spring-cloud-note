package com.ky.note.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Base64;

/**
 * @author kamyam
 * @date 2019/6/15 17:07
 * @EnableAuthorizationServer 开启认证服务器自动配置，必须实现AuthorizationServerConfigurer
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private DataSource dataSource;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    public AuthorizationServerConfig(DataSource dataSource, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public JdbcTokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }


    /**
     * 1.当grant_types = password，必须注入AuthenticationManager
     * 2.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService).tokenStore(jdbcTokenStore());
    }

    /**
     * 配置获取客户端的方式，提供了inMemory（从内存获取）和jdbc（从数据库获取）两种方式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //从数据库获取客户端信息
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

  /*  @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }*/


}
