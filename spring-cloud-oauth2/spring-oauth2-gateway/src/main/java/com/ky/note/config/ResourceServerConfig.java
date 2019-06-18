package com.ky.note.config;

import com.ky.note.service.CustomRemoteTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author kamyam
 * @date 2019/6/18 21:13
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ResourceServerProperties resource;

    @Bean
    @Primary
    public ResourceServerTokenServices customRemoteTokenServices() {
        CustomRemoteTokenServices remoteTokenServices = new CustomRemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(resource.getTokenInfoUri());
        remoteTokenServices.setClientId(resource.getClientId());
        remoteTokenServices.setClientSecret(resource.getClientSecret());
        return remoteTokenServices;
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //defaultTokenServices.setTokenStore(new JdbcTokenStore(dataSource));
        //defaultTokenServices.setClientDetailsService(new JdbcClientDetailsService(dataSource));
        resources.tokenServices(customRemoteTokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/**").authenticated().anyRequest().permitAll();
    }

}
