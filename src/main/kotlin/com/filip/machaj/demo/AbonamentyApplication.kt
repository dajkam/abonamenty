package com.filip.machaj.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.transaction.annotation.EnableTransactionManagement


@SpringBootApplication
@EnableTransactionManagement
@PropertySource( "classpath:application.properties" )
@ComponentScan( "com.filip.machaj" )
class AbonamentyApplication

fun main(args: Array<String>) {
    SpringApplication.run(AbonamentyApplication::class.java, *args)




}
