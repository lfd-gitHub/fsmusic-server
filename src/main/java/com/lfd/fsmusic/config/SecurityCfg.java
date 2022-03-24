package com.lfd.fsmusic.config;

import java.util.Arrays;

import com.lfd.fsmusic.config.exceptions.RestAccessDeniedHandler;
import com.lfd.fsmusic.config.exceptions.RestAuthenticationEntryPoint;
import com.lfd.fsmusic.filter.JwtAuthorizationFilter;
import com.lfd.fsmusic.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityCfg extends WebSecurityConfigurerAdapter {

    public static final String SECRET = "13ebccd";
    public static final long EXPIRE_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_KEY = "Authorization";

    private UserService uService;
    private RestAccessDeniedHandler aDeniedHandler;
    private RestAuthenticationEntryPoint rEntryPoint;

    @Autowired
    public void setuService(UserService uService) {
        this.uService = uService;
    }

    @Autowired
    public void setaDeniedHandler(RestAccessDeniedHandler aDeniedHandler) {
        this.aDeniedHandler = aDeniedHandler;
    }

    @Autowired
    public void setrEntryPoint(RestAuthenticationEntryPoint rEntryPoint) {
        this.rEntryPoint = rEntryPoint;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/profile")
                .antMatchers("/profile/**")
                .antMatchers("/v3/**")
                .antMatchers("/api/token")
                .antMatchers("/api/setting/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().csrf().disable()
                .logout().logoutUrl("/api/user/logout")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(aDeniedHandler)
                .authenticationEntryPoint(rEntryPoint)
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),uService))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        /* 是否允许请求带有验证信息 */
        corsConfiguration.setAllowCredentials(true);
        /* 允许访问的客户端域名 */
        // corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        /* 允许服务端访问的客户端请求头 */
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        /* 允许访问的方法名,GET POST等 */
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uService);
    }
}
