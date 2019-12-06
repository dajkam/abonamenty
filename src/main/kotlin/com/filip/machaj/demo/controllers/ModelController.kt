package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.dto.ModelDTO
import com.filip.machaj.demo.service.ModelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/model")
class ModelController {
    @Autowired
    private lateinit var service:ModelService

    @GetMapping("/download",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))

    fun getModel() = service.getModel()

    @PutMapping(
            "/insert",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertModel(@RequestBody model: ModelDTO) = service.insertModel(model)

    @DeleteMapping(
            value = ["delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteModel(@PathVariable(name = "id")id:Int) = service.deleteModel(id)

    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateModel(@RequestBody model: ModelDTO): ModelDTO = service.updateModel(model)
}
