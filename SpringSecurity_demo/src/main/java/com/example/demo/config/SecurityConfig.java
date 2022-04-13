package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.handle.MyAccessDeniedHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private MyAccessDeniedHandler myAccessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/loginpage")
			.successForwardUrl("/")
			.failureForwardUrl("/fail");
		
		http.authorizeHttpRequests()
			.antMatchers("/loginpage").permitAll()
			.antMatchers("/adminpage").hasAuthority("admin")
			.antMatchers("/managerpage").hasRole("manager")
			.antMatchers("/employeepage").hasAnyRole("manager", "employee")
			.anyRequest().authenticated();
		
		http.logout()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/loginpage")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	
		http.exceptionHandling()
			.accessDeniedHandler(myAccessDeniedHandler);
		
		http.rememberMe()
			.userDetailsService(userDetailsService)
			.tokenValiditySeconds(60);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}