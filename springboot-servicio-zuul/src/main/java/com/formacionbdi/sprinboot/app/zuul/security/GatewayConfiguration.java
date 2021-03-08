package com.formacionbdi.sprinboot.app.zuul.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class GatewayConfiguration  extends ResourceServerConfigurerAdapter {

    @Value("${config.security.oauth.jwt.key}")
    private String jwt;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/productos/listar", "/api/items/listar", "/api/usuarios/usuarios").permitAll()
                .antMatchers(HttpMethod.GET,"/api/productos/ver/{id}","/api/items/ver/{id}/cantidad/{cantidad}", "/api/usuarios/usuarios/{id}")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/productos/**", "/api/items/**","/api/usuarios/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
       resources.tokenStore(tokenStore());
    }


    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accestokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accestokenConverter() {

        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwt);
        return tokenConverter;
    }
}
