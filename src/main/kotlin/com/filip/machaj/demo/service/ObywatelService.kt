package com.filip.machaj.demo.service

import com.filip.machaj.demo.model.dane.Obywatel
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random


@Service("Obywatel Service")
class ObywatelService {
    fun getObywatel():List<Obywatel> = listOf(

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

    fun insertObywatel(obywatel: Obywatel):Obywatel{
        obywatel.id = Random.nextLong(1,100)
        return obywatel
    }

    fun deleteObywatel(id:Long) :Boolean = false

    fun updateObywatel(obywatel: Obywatel) : Boolean = true
}