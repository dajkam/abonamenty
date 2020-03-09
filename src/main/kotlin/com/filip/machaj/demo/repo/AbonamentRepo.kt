package com.filip.machaj.demo.repo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Abonament
import com.filip.machaj.demo.model.dane.AbonamentInfo
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface AbonamentRepo : CrudRepository<Abonament, Long> {

    @Query("select * from abonament where data_zakonczenia > ?1 ", nativeQuery = true)
    fun findByZakonczenie(zak: Date): Iterable<Abonament>

    @Query("select * from abonament where id=(select max(id) from abonament)", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findLast(): Abonament

    @Query("""
      select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany, abonament.uwagi,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id, abonament.kiedy_utworzono, abonament.kiedy_zmodyfikowano from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id
      
        
    """, nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findAllAbonamentInfo(): Iterable<AbonamentInfo>

    @Modifying(clearAutomatically = true, flushAutomatically = true) // mandatory if native quaries are modying something in the dataBase
    @Transactional
    @Query("""
        update abonament
        set czy_zarchiwizowany = true 
        where id = :id""", nativeQuery = true)
    fun archwizujAbonament(@Param("id") id: Long) // tego typu funkcje muszą być typu void.


    @Query("""
      select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany, abonament.uwagi,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id, abonament.kiedy_utworzono, abonament.kiedy_zmodyfikowano from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id
order by obywatel.pesel
      
        
    """, nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun findAllAbonamentInfoOrderByPesel(): Iterable<AbonamentInfo>

    @Query("""
    select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany, abonament.uwagi,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id, abonament.kiedy_utworzono, abonament.kiedy_zmodyfikowano from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id
where  
 lower(abonament.sektor) like ?1
or lower(pojazd.nr_rejstracyjny_pojazdu) like ?1 or lower(marka.nazwa) like ?1
or lower(model.nazwa) like ?1 or lower(obywatel.imie) like ?1
or lower(obywatel.nazwisko) like ?1 or lower(obywatel.pesel) like ?1
or lower(cast(abonament.id as varchar(36) )) like ?1 or lower(cast(abonament.data_rozpoczecia as varchar(36) )) like ?1
or lower(cast(abonament.data_zakonczenia as varchar(36) )) like ?1

""", nativeQuery = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    fun szukaj(fraza: String): Iterable<AbonamentInfo>


}
