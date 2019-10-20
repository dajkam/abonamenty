package com.filip.machaj.demo.repo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Abonament
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AbonamentRepo : CrudRepository<Abonament, Long> {

    @Query("select * from abonament where data_zakonczenia > ?1 ", nativeQuery = true)
    fun findByZakonczenie(zak:Date):Iterable<Abonament>

    @Query("select * from abonament where id=(select max(id) from abonament)",nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findLast():Abonament
}