package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.controllers.queryobjests.AbonamentByZak
import com.filip.machaj.demo.dto.AbonamentDTO
import com.filip.machaj.demo.service.AbonamentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime


@RestController
@RequestMapping("abonament")
class AbonamentController {

    @Autowired
    private lateinit var service : AbonamentService

    @GetMapping("/download",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getAbonament() = service.getAbonament()

    @PutMapping(
            "/insert",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )

    fun insertAbonament(@RequestBody abonament:AbonamentDTO) = service.insertAbonament(abonament)

    @DeleteMapping(
            value = ["delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteAbonament(@PathVariable(name = "id")id:Long) = service.deleteAbonament(id)

    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )

    fun updateAbonament(@RequestBody abonament: AbonamentDTO): AbonamentDTO = service.updateAbonament(abonament)


    @PostMapping(
            value = ["/zakonczenie"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)

    )
    fun getAbonamentByZakonczenie(
            @RequestBody payload: AbonamentByZak
    ):Iterable<AbonamentDTO> = service.getAbonamentByZakonczenie(payload.zak)

    @GetMapping(
            value = ["/full"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun findAllAbonamentInfo() = service.findAllAbonamentInfo()
    @PostMapping(
            value = ["archiwizuj/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun archwizujAbonament(@PathVariable(name = "id")id:Long) = service.archiwizujAbonament(id)


    @GetMapping(
            value = ["/full/pesel"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun findAllAbonamentInfoOrderByPesel() = service.findAllAbonamentInfoOrderByPesel()

@GetMapping(
        value = ["/szukaj/{fraza}"],
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
)
fun szukaj(@PathVariable(name = "fraza")fraza:String) = service.szukaj(("%"+fraza.toLowerCase()+"%"))

    @GetMapping(
            value = ["/full/get/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getAbonamentInfoById(@PathVariable(name = "id")id:Long) = service.getAbonamentInfoById(id)
    @GetMapping(
            value = ["/data"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getCurrentData(): String? {
        val currentDateTime : LocalDateTime = LocalDateTime.now()
        return currentDateTime.format(DateTimeFormatter.ISO_DATE)
    }
}
