package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.dto.MarkaDTO
import com.filip.machaj.demo.service.MarkaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("marka")
class MarkaController {
    @Autowired
    private lateinit var service : MarkaService

    @GetMapping("/download",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))

    fun getMarka() = service.getMarka()

    @PutMapping(
            "/insert",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )

    fun insertMarka(@RequestBody marka:MarkaDTO) = service.insertMarka(marka)

    @DeleteMapping(
            value = ["delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )

    fun deleteMarka(@PathVariable(name = "id")id:Long) = service.deleteMarka(id)

    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )

    fun updateMarka(@RequestBody marka: MarkaDTO):MarkaDTO = service.updateMarka(marka)
}