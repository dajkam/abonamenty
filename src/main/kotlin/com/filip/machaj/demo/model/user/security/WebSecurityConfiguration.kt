package com.filip.machaj.demo.model.user.security

import com.filip.machaj.demo.model.user.Role
import com.filip.machaj.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.expression.WebExpressionVoter
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import java.util.*

class WebSecurityConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var service: UserService
    @Autowired
    lateinit var unautthorizedHandler: AuthenticationEntryPoint
    @Autowired
    lateinit var successHandler: WebSecurityAuthSuccessHandler

    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider( authenticationProvider())
    }

    override fun configure(http: HttpSecurity?) {
        http
                ?.csrf()?.disable()
                ?.exceptionHandling()
                ?.authenticationEntryPoint(unautthorizedHandler)
                ?.and()
                ?.authorizeRequests()
                // kto ma dostęp do poniższych ścieżek API => Wszyscy zalogowani Strażnik i Uliczny => trzeba późnie wyszczegółówić
                ?.antMatchers("/obywatel/")?.authenticated()
                ?.antMatchers("/obywatel/**")?.authenticated()
                ?.antMatchers("/pojazd/")?.authenticated()
                ?.antMatchers("/pojazd/**")?.authenticated()
                ?.antMatchers("/abonament/")?.authenticated()
                ?.antMatchers("/abonament/**")?.authenticated()
                ?.antMatchers("/model/")?.authenticated()
                ?.antMatchers("/model/**")?.authenticated()
                ?.antMatchers("/marka/")?.authenticated()
                ?.antMatchers("/marka/**")?.authenticated()

                //admin

                ?.antMatchers("/user/")?.hasAnyAuthority(Role.ADMIN.poziom)
                ?.antMatchers("/user/**")?.hasAnyAuthority(Role.ADMIN.poziom)

                ?.and()
                ?.formLogin()
                ?.successHandler(successHandler)
                ?.failureHandler(SimpleUrlAuthenticationFailureHandler())
                ?.and()
                ?.logout()
    }

    @Bean
    private fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(service)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    private fun encoder(): PasswordEncoder = BCryptPasswordEncoder(11)

    @Bean
   fun accessDecisionManager(): AccessDecisionManager{
        val decisionvoters = Arrays.asList(
                WebExpressionVoter(),
                RoleVoter(),
                AuthenticatedVoter()
        )
       return UnanimousBased(decisionvoters)
    }




}
