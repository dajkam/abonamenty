package com.filip.machaj.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.atomic.AtomicLong
@RestController
class gretcontroller {
    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            gret(counter.incrementAndGet(), "Hello, $name")
}