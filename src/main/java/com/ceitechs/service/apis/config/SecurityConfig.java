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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                //.addFilterAfter(authTokenProcessingFilter)
                .addFilter(authTokenProcessingFilter)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,  "apis/v1/authenticate").permitAll() //Authentication
                .antMatchers(HttpMethod.GET, serverContextPath + "/account-verification/*").permitAll() // Account verification
                .antMatchers(HttpMethod.OPTIONS, serverContextPath + "/**").permitAll()//allow CORS option calls
                .antMatchers(HttpMethod.GET,serverContextPath + "/properties").permitAll() //allow properties search globally
                .antMatchers(HttpMethod.POST, serverContextPath + "/users").permitAll() //allow user registration
                //.antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/providers/**").hasRole("ADMIN")
                //.antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')")
                //.anyRequest().authenticated();
                .antMatchers("/users/**", serverContextPath + "/properties/**").access("hasRole('USER')")
                .antMatchers("/properties/**").authenticated();
//                .antMatchers("app/api/**").authenticated()
//                .antMatchers("app/providers/api/**").authenticated();

    }
}
