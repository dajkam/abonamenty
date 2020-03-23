package com.filip.machaj.demo.model.user.security

import com.filip.machaj.demo.model.user.Role
import com.filip.machaj.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.vote.AuthenticatedVoter
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.access.vote.UnanimousBased
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.expression.WebExpressionVoter
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import java.util.*

import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authenticationEntryPoint: WebSecurityEntryPoint

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass"))
                .authorities("ROLE_USER")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/securityNone").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin()



    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }




}
