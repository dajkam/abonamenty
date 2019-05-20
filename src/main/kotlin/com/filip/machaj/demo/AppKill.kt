package com.filip.machaj.demo

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import kotlin.system.exitProcess


@RestController
@RequestMapping("/app")

class AppKill {

    @GetMapping(value = ["/kill"]

    )
    @ResponseStatus(value = HttpStatus.OK)
    fun exitProcess(){
        exitProcess(1)

    }
}