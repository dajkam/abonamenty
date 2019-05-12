package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.model.dane.Obywatel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*
import java.awt.PageAttributes

@RestController
@RequestMapping("/obywatel")

class ObywatelController {
    @GetMapping(value = ["/download"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getObywatel():List<Obywatel>{
        return listOf(
                Obywatel(
                        1123,
                        "234555069",
                        "hary",
                        false

                ),
                Obywatel(
                        6666,
                        "8o7070807070",
                        "johny",
                        true
                )

        )
    }
}