package com.filip.machaj.demo.repo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Pojazd
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PojazdRepo: CrudRepository<Pojazd,Long> {
    @Query("select * from pojazd where model like ?1",nativeQuery = true)
    fun findPojazdByModel(model:String): Iterable<Pojazd>
    @Query("select * from pojazd where id=( select max(id) from pojazd)", nativeQuery = true )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findLast():Pojazd
}