package com.pgs.java_dev.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler mySavedRequestAwareAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("user1")
               .password("{noop}password1")
               .roles("USER")
               .and()
               .withUser("user2")
               .password("{noop}password2")
               .roles("MODERATOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/customers").hasAnyRole("MODERATOR", "USER")
                .antMatchers(HttpMethod.POST, "/customers").hasAnyRole("MODERATOR")
                .antMatchers(HttpMethod.PUT, "/customers/{id}").hasAnyRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/customers/{id}").hasAnyRole("MODERATOR")
                .antMatchers(HttpMethod.GET, "/products").hasAnyRole("MODERATOR", "USER")
                .antMatchers(HttpMethod.POST, "/products").hasAnyRole("MODERATOR")
                .antMatchers(HttpMethod.PUT, "/products/{id}").hasAnyRole("MODERATOR", "USER")
                .antMatchers(HttpMethod.DELETE, "/products/{id}").hasAnyRole("MODERATOR", "USER")
                .antMatchers(HttpMethod.GET, "/customers/{id}/products").hasAnyRole("MODERATOR", "USER")
                .antMatchers(HttpMethod.POST, "/customers/{id}/products").hasAnyRole("MODERATOR", "USER")
                .antMatchers("/users").hasRole("MODERATOR")
                .and()
                .formLogin()
                .successHandler(mySavedRequestAwareAuthenticationSuccessHandler)
                .and()
                .headers().frameOptions().disable()
                .and()
                .logout();
    }
}
