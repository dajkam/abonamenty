package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.controllers.queryobjests.PojazdByModel
import com.filip.machaj.demo.dto.PojazdDTO
import com.filip.machaj.demo.model.dane.Pojazd
import com.filip.machaj.demo.service.PojazdService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/pojazd")

class PojazdController {

    @Autowired
    private lateinit var service : PojazdService

    @GetMapping("/download",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))

    fun getPojazd() = service.getPojazd()

    @PutMapping(
            "/insert",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )

    fun insertPojazd(@RequestBody pojazd: PojazdDTO) = service.insertPojazd(pojazd)

    @DeleteMapping(
            value = ["delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deletePojazd(@PathVariable(name = "id")id:Long) = service.deletePojazd(id)

    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updatePojazd(@RequestBody pojazd: PojazdDTO): PojazdDTO = service.updatePojazd(pojazd)

    @PostMapping(
            value = ["/model"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)

    )
    fun getPojazdByModel(
            @RequestBody payload: PojazdByModel
    ):Iterable<PojazdDTO> = service.getPojazdByModel(payload.model)

    @GetMapping(
            value = ["/last"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getLastPojazd(): Pojazd = service.findLastPojazd()

    @GetMapping(
            value = ["/marka/model"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getAllPojModMar() = service.getAllPojModMar()

    @PostMapping(
            value = ["archiwizuj/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun archwizujPojazd(@PathVariable(name = "id")id:Long) = service.archwizujPojazd(id)

    @PostMapping(
            value = ["odnow/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun odnowPojazd(@PathVariable(name = "id")id:Long) = service.odnowPojazd(id)

    @GetMapping(
            value = ["/szukaj/{fraza}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun szukaj(@PathVariable(name = "fraza")fraza:String) = service.szukaj(("%"+fraza.toLowerCase()+"%"))
}
