package com.example.hellospringsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("securityDataSource")
    private DataSource securityDataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //tự set tài khoản và mật khẩu
//        try {
//            auth.inMemoryAuthentication()
//                    .withUser("user").password("{noop}password").roles("USER")
//                    .and()
//                    .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        // lấy tài khoản từ JDBC
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/users").hasAnyRole("EMPLOYEE")
                .antMatchers("/admin").hasAnyRole("MANAGER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
