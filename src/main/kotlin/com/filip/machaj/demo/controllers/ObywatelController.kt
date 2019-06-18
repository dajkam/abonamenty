package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.controllers.queryobjests.ObywatelByImie
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
    fun updateObywatel(@RequestBody obywatel: ObywatelDTO): ObywatelDTO = service.updateObywatel(obywatel)

    // metody dodane przezemnie

    @PostMapping(
            value = ["/imie"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)



    )
    fun getObywatelByImie(
            @RequestBody payload: ObywatelByImie
    ):Iterable<ObywatelDTO> = service.getObywatelByImie(payload.imie)


    @GetMapping(
            value = ["/last"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getLastObywatel():Obywatel = service.findLastObywatel()
  
    @PostMapping(
            value = ["/nazwisko"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getObywatelByNazwisko(@RequestBody payload: OBywatelFindByNazwisko): Iterable<ObywatelDTO> = service.findByNazwisko(payload.nazwisko)


}