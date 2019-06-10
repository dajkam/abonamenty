package com.filip.machaj.demo.repo

import com.filip.machaj.demo.model.dane.Abonament
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AbonamentRepo : CrudRepository<Abonament, String> {

    @Query("select * from abonament where data_zakonczenia > ?1 ", nativeQuery = true)
    fun findByZakonczenie(zak:Date):Iterable<Abonament>
}