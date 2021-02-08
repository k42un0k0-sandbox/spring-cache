package com.example.demo.config;

import com.example.demo.spring.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/users/**").hasRole("USER")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login-error")
                .and()
                .csrf().disable()
                .cors().disable()
                .sessionManagement()
                .invalidSessionUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .exceptionHandling()
                .and()
                .headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
