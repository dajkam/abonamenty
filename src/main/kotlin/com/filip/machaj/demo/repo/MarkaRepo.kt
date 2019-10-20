package com.filip.machaj.demo.repo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Marka
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MarkaRepo:CrudRepository<Marka,Long> {

    @Query("select * from marka where id=( select max(id) from marka)", nativeQuery = true )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findLast():Marka


}