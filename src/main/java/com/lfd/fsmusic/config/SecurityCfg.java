package com.lfd.fsmusic.config;

import com.lfd.fsmusic.config.exceptions.RestAccessDeniedHandler;
import com.lfd.fsmusic.config.exceptions.RestAuthenticationEntryPoint;
import com.lfd.fsmusic.filter.JwtAuthenticationFilter;
import com.lfd.fsmusic.filter.JwtAuthorizationFilter;
import com.lfd.fsmusic.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
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
                .antMatchers("/v3/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(aDeniedHandler)
                .authenticationEntryPoint(rEntryPoint)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uService);
    }
}
