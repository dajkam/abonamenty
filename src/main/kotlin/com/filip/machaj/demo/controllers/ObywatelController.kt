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
@CrossOrigin(origins = ["http://localhost:63342/"], maxAge = 3600, allowedHeaders = ["*"], allowCredentials = "true" )

class ObywatelController {
    @Autowired
    private lateinit var service : ObywatelService




    @GetMapping(value = ["/download"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
  //  fun getObywatel(): Iterable<Obywatel> = service.getObywatel()
    @CrossOrigin(origins = ["http://localhost:63342/"])// zmieniono dla javascript CORS
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
  
    /*@PostMapping(
            value = ["/nazwisko"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
   fun getObywatelByNazwisko(@RequestBody payload: OBywatelFindByNazwisko): Iterable<ObywatelDTO> = service.findByNazwisko(payload.nazwisko)*/

    @PostMapping(
            value = ["archiwizuj/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun archwizujObywatela(@PathVariable(name = "id")id:Long) = service. archiwizujObywatela(id)

    @PostMapping(
            value = ["odnow/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun odnowObywatela(@PathVariable(name = "id")id:Long) = service.odnowObywatela(id)

    @GetMapping(
            value = ["/szukaj/{fraza}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun szukaj(@PathVariable(name = "fraza")fraza:String) = service.szukaj(("%"+fraza.toLowerCase()+"%"))

    @GetMapping(
            value = ["/get/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getObywatelById(@PathVariable(name = "id")id:Long) = service.getObywatelById(id)

}
