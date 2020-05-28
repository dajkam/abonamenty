package com.filip.machaj.demo.model.user.security

import com.filip.machaj.demo.model.user.Role
import com.filip.machaj.demo.model.user.User
import com.filip.machaj.demo.service.UserService
import com.github.mvysny.karibudsl.v8.autoViewProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
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
import javax.sql.DataSource


@Configuration
@EnableWebSecurity

class WebSecurityConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authenticationEntryPoint: WebSecurityEntryPoint
   // @Autowired
   // lateinit var service:UserService

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal( auth: AuthenticationManagerBuilder) {


       // auth.eraseCredentials(true)

     //  val users: Iterable<User> = service.downLoadUsers()

       /* if(users.count() == 0){

           val junit:JUnitCore  =  JUnitCore()
           val result:Result = junit.run( SecurityInitializationTest)
            val users: Iterable<User> = service.downLoadUsers()
        }*/

      /* for (user in users){

            auth.inMemoryAuthentication()
                    .withUser(user.email).password(encoder().encode(user.haslo))
                    .authorities(user.role)
        }*/

      //  auth.userDetailsService(service).passwordEncoder(encoder())





        auth.jdbcAuthentication().dataSource(dataSource())
                .usersByUsernameQuery("select * from user where email = ?")
                .authoritiesByUsernameQuery("")


    }
  /*  @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("du"))
                .authorities(Role.ADMIN.poziom)
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("du1"))
                .authorities(Role.ULICZNY.poziom)


        //   auth.authenticationProvider(DaoAuthenticationProvider())
    }*/
    fun configureLocal(auth: AuthenticationManagerBuilder, rola: String, nik: String, pass: String) {
        auth.inMemoryAuthentication()
                .withUser(nik).password(encoder().encode(pass))
                .authorities(rola)

    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/uliczny.html").authenticated() // dodawanie z poza front endu działa tylko przez curl curl -u user:du żeby działał postman trzeba .authenticeded() zmienić na .permitAll()
                .anyRequest().authenticated()// dodawanie z poza front endu działa tylko przez curl curl -u user:du żeby działał postman trzeba .authenticeded() zmienić na .permitAll()
                .and()
                .csrf().disable()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin()
                .successHandler(WebSecurityAuthSuccessHandler())



    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
fun dataSource(): DataSource {
val  dataSource:DriverManagerDataSource =  DriverManagerDataSource();
    dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
    // adjust the following to your environment
    dataSource.setUrl("jdbc:hsqldb:hsql://localhost/workdb");
    dataSource.setUsername("sa");
    dataSource.setPassword("");
        return dataSource
       //val   dataSourceBuilder = DataSourceBuilder.create();
       // return dataSourceBuilder.build()
}




}










