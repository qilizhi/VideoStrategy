package com.dz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.dz.service.SpringDataUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SpringDataUserDetailsService userDetailsService;
	//@Autowired
	//private MyAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/error","/css/**","/static/**","/images/**","layout/**","views/**","/assets/css/**","/assets/js/**").permitAll()
			//	.antMatchers("/user/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/user/list")
				.failureUrl("/loginfail").permitAll()
				.and().logout().logoutSuccessUrl("/login").permitAll().invalidateHttpSession(true).and().rememberMe();
		/*http.authorizeRequests()
		.antMatchers("/resources/**", "/error", "/hello").permitAll()
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/login").permitAll().failureUrl("/loginfail")
		.permitAll().and().logout().logoutUrl("loginout");*/
	}
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        //web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
	        web.ignoring().antMatchers("/resources/**","/error","/css/**","/static/**","/images/**","layout/**","views/**","assets/**/**");
	    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password")
				.roles("USER");
		//auth.authenticationProvider(authenticationProvider);
		 auth.userDetailsService(userDetailsService).passwordEncoder(new StandardPasswordEncoder());
		// auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema();

	}
	/*@Bean
	public DaoAuthenticationProvider daoAuthenticationP(){
		DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
		ReflectionSaltSource saltSource= new ReflectionSaltSource();
		saltSource.setUserPropertyToUse("username");
		daoAuthenticationProvider.setSaltSource(saltSource);
		return daoAuthenticationProvider ;
	}
	@Bean
	public SpringDataUserDetailsService springDataUserDetailsService() {
		return new SpringDataUserDetailsService();
	}*/

}