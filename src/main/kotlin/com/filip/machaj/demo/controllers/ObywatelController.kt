package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.model.dane.Obywatel
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
    @GetMapping(value = ["/download"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getObywatel():List<Obywatel>{
        return listOf(
                Obywatel(
                        1,
                        "12345678901",
                        "xr-9867",
                        "Janusz",
                        "Korwin-Mikke",
                        Date(27,10,1945,0,0,0),
                        "Woronicza 16 Warszawa ",
                        false





                ),
                Obywatel(
                        2,
                        "09876579799",
                        "wr-1345",
                        "Jerzy",
                        "Urban",
                        Date(3,8,1933,0,0,0),
                        "Róży Luksemburg 44 Łódz",
                        true





                )


        )
    }

    @PutMapping(
            value = ["/insert"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)

    )
    fun insertObywatel(@RequestBody obywatel: Obywatel):Obywatel{
        obywatel.id = Random.nextLong(1,100)
        return obywatel

    }

    @DeleteMapping(
            value = ["/delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteObywatel(@PathVariable(name = "id")id:Long): Boolean {
        println("Usunięto obywatela o id $id")
        return true
    }

    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateObywatel(@RequestBody obywatel: Obywatel):Obywatel{
        obywatel.imie = "Jankiel"
        obywatel.nazwisko = "Żyd"
        obywatel.kiedy_zmodyfikowano = System.currentTimeMillis()
        obywatel.wieksza()
        print("zmieniono imie na Jankiel a nazwisko na Żyd \n")
        print("modyfikacji dokonano dokładnie w ${obywatel.kiedy_zmodyfikowano}\n")
        return obywatel
    }
}