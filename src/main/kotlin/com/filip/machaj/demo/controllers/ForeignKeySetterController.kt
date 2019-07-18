package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.model.dane.ForeignKeySetter
import com.filip.machaj.demo.service.ForeignKeySetterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.awt.PageAttributes

@RestController
@RequestMapping("/key")
class ForeignKeySetterController {

    @Autowired
    private lateinit var service: ForeignKeySetterService

    @PutMapping(
            "/set",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)

    )
    fun setForeignKeys(@RequestBody foreignkeysetter:ForeignKeySetter) = service.setForeignKeys(foreignkeysetter)
}