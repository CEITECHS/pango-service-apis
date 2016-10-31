package com.ceitechs.service.apis.config;

import com.ceitechs.service.apis.security.AuthFailure;
import com.ceitechs.service.apis.security.AuthSuccess;
import com.ceitechs.service.apis.security.AuthenticationTokenProcessingFilter;
import com.ceitechs.service.apis.security.EntryPointUnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author iddymagohe on 10/30/16.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Value("${server.context-path}")
    private String serverContextPath;

    @Autowired
    private AuthFailure authFailure;

    @Autowired
    private AuthSuccess authSuccess;

    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Lazy(true)
    PasswordEncoder passwordEncryptor;

    @Autowired
    private AuthenticationTokenProcessingFilter authTokenProcessingFilter;

    @Autowired
    public void configureAuthBuilder(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncryptor);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .addFilter(authTokenProcessingFilter)
                .authorizeRequests()
                .antMatchers("/authenticate*").permitAll() //Authentication
                .antMatchers(HttpMethod.GET, "/properties").permitAll() // Properties Search
                .antMatchers(HttpMethod.POST, "/users").permitAll() // User Registration
                .antMatchers(HttpMethod.OPTIONS).permitAll() // OPTIONS for some JS frameworks
                .antMatchers(HttpMethod.GET, "/account-verification/*").permitAll() // Account verification
                .anyRequest().authenticated();
        //TODO define advanced user access pattern with roles etc.

    }


}
