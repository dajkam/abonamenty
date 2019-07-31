package com.filip.machaj.demo.repo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Obywatel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ObywatelRepo : CrudRepository<Obywatel, Long>  {
    @Query("select * from obywatel where imie = ?1", nativeQuery = true)
    fun findByImie(imie:String): Iterable<Obywatel>

    @Query("select * from obywatel where id=( select max(id) from obywatel)", nativeQuery = true )
    fun findLast():Obywatel


    @Query("select * from obywatel where nazwisko like ?1", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findByNazwisko(nazwisko:String): Iterable<Obywatel>

}