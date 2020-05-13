package com.filip.machaj.demo.repo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.ModMar
import com.filip.machaj.demo.model.dane.Model
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ModelRepo: CrudRepository<Model,Int> {

    @Query("select * from model where id=( select max(id) from model)", nativeQuery = true )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findLast():Model

    @Query("""select model.id, model.nazwa as nazwa, marka.nazwa as marka
            from model 
            join marka
            on marka.id = model.marka_id """, nativeQuery = true )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun getAllModMar():Iterable<ModMar>
}
