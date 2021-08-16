package com.manager.booklibrary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomUserDetailService customUserDetailService;
    
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/books/save/**", "/api/books/update/**", "/api/books/delete/**")
                .hasAnyRole("MANAGER")
                .antMatchers("/api/authors/save/**", "/api/authors/update/**", "/api/authors/delete/**")
                .hasAnyRole("MANAGER")
                .antMatchers("/swagger-ui.html/**")
                .hasAnyRole("MANAGER")
                .antMatchers("/api/readers/**")
                .hasAnyRole("MANAGER", "USER")
                .antMatchers("/api/carts/**")
                .hasAnyRole("USER", "MANAGER")
                .anyRequest().permitAll()
                .and().formLogin();
    }
}
