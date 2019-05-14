package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.service.ObywatelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*
import java.awt.PageAttributes
import javax.websocket.server.PathParam
import kotlin.random.Random

@RestController
@RequestMapping("/obywatel")

class ObywatelController {
    @Autowired
    private lateinit var service : ObywatelService


    @GetMapping(value = ["/download"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getObywatel() = service.getObywatel()
    @PutMapping(
            value = ["/insert"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)

    )
    fun insertObywatel(@RequestBody obywatel: Obywatel) = service.insertObywatel(obywatel)


    @DeleteMapping(
            value = ["/delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteObywatel(@PathVariable(name = "id")id:Long): Boolean = service.deleteObywatel(id)
    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateObywatel(@RequestBody obywatel: Obywatel):Boolean = service.updateObywatel(obywatel )
}