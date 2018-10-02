package ua.pp.darknsoft.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

   @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication() //.passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user")
                .password("{noop}password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
