package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.dto.ObywatelDTO
import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.service.ObywatelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/obywatel")

class ObywatelController {
    @Autowired
    private lateinit var service : ObywatelService


    @GetMapping(value = ["/download"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
  //  fun getObywatel(): Iterable<Obywatel> = service.getObywatel()

    fun getObywatel() = service.getObywatel()

    @PutMapping(
            value = ["/insert"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)

    )
   // fun insertObywatel(@RequestBody obywatel: Obywatel) = service.insertObywatel(obywatel)

    fun insertObywatel(@RequestBody obywatel: ObywatelDTO) = service.insertObywatel(obywatel)


    @DeleteMapping(
            value = ["/delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteObywatel(@PathVariable(name = "id")id:Long): Unit = service.deleteObywatel(id)
    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateObywatel(@RequestBody obywatel: Obywatel): Obywatel = service.updateObywatel(obywatel)

    @GetMapping(
            value = ["test"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )

    private fun testBazy(){



    }
}