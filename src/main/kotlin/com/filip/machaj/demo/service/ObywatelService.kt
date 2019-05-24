package com.filip.machaj.demo.service

import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.repo.ObywatelRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service("Obywatel Service")
class ObywatelService {
    

    @Autowired
    lateinit var repo : ObywatelRepo





    fun getObywatel(): Iterable<Obywatel> = repo.findAll()

    fun insertObywatel(obywatel: Obywatel):Obywatel = repo.save(obywatel)

    fun deleteObywatel(id:Long) = repo.deleteById(id)

    fun updateObywatel(obywatel: Obywatel) : Obywatel = repo.save(obywatel)
}