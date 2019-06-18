package com.ky.note.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


import javax.sql.DataSource;

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

    /**
     * 将token存储在oauth_access_token
     */
    @Bean
    public JdbcTokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }


    @Bean
    public JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }


    @Bean
    public JdbcApprovalStore jdbcApprovalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * 1.当grant_types = password，必须注入AuthenticationManager
     * 2.TokenStore: 默认InMemoryTokenStore，支持JdbcTokenStore，RedisTokenStore，JwkTokenStore，JwtTokenStore
     * 3.UserDetailsService 刷新token必需
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(jdbcTokenStore())
                .authorizationCodeServices(jdbcAuthorizationCodeServices())
                .approvalStore(jdbcApprovalStore());
    }


    /**
     * 配置获取客户端的方式，提供了inMemory（从内存获取）和jdbc（从数据库获取）两种方式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //从数据库获取客户端信息
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }


    /**
     * 1.allowFormAuthenticationForClients：
     * 调用/oauth/token获取access_token的时候需要传参数client_id和client_secret
     * 默认在请求头中获取：Authorization Basic Zmlyc3QtY2xpZW50Om5vb25ld2lsbGV2ZXJndWVzcw==
     * 如果想从请求参数中获取需要配置allowFormAuthenticationForClients = true;开启clientCredentialsTokenEndpointFilter
     * 2.tokenKeyAccess:
     * 开放/oauth/token_key使用JWT的时候用来回去公钥
     * 3.checkTokenAccess：
     * 开放/oauth/check_token验证token是否有效
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");

    }


}
