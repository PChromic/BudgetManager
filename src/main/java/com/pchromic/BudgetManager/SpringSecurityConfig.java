package com.pchromic.BudgetManager;

import com.querydsl.core.annotations.Config;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Config
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/operations/**").permitAll()
                .antMatchers(HttpMethod.GET,"/operations/**").permitAll()
                .antMatchers(HttpMethod.GET,"/file/**").permitAll()
                .anyRequest().authenticated();
    }
}
