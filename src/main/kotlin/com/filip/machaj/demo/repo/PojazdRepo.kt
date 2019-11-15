package com.filip.machaj.demo.repo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.PojModMar
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

    @Query("""
        
select pojazd.id,pojazd.czy_zarchiwizowany,pojazd.kiedy_utworzono,pojazd.kiedy_zmodyfikowano,pojazd.kolor,pojazd.nr_rejstracyjny_pojazdu,pojazd.rok_produkcji,pojazd.uwagi, marka.nazwa as marka,model.nazwa as model, pojazd.model_id,pojazd.obywatel_id, model.marka_id from pojazd
  join model 
on pojazd.model_id = model.id
  join marka
on model.marka_id = marka.id
        
    """, nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findAllPojModMar():Iterable<PojModMar>

}