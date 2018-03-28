package com.prosegur.guvnor.negocio.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.prosegur.guvnor.negocio.controller.MappingsController;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").hasAnyRole(Rol.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(MappingsController.LOGIN)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = auth.inMemoryAuthentication();
    	configurer.passwordEncoder(passwordEncoder());
    	for (Usuario usuario: Usuario.values()) {
    		configurer.withUser(usuario.name()).password(usuario.getPassword()).roles(usuario.getRolToString());
    	}
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
