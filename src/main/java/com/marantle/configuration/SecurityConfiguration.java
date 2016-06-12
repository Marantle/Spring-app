package com.marantle.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/", "/products", "/webjars/**", "/css/**", "/js/**", "/images/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();


        //httpSecurity.authorizeRequests().antMatchers("/").permitAll().and().
        //disable security to enable h2 console
        //httpSecurity.authorizeRequests().antMatchers("/console/*").permitAll();
        //httpSecurity.csrf().disable();

        // some hack to disable csrf protection for h2 console
        httpSecurity.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
            private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
            private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/console/.*", null);

            @Override
            public boolean matches(HttpServletRequest request) {
                if(allowedMethods.matcher(request.getMethod()).matches())
                    return false;
                if(apiMatcher.matches(request))
                    return false;
                return true;
            }
        });
        httpSecurity.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("user").roles("USER");
    }
}