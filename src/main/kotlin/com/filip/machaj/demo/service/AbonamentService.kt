package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.AbonamentDTO
import com.filip.machaj.demo.model.dane.Abonament
import com.filip.machaj.demo.repo.AbonamentRepo
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Service
import java.util.*

@Service("Abonament Service")

class AbonamentService {
    @Autowired
    lateinit var repo : AbonamentRepo

    fun getAbonament():Iterable<AbonamentDTO> = repo.findAll().map{it -> AbonamentDTO(it) }

    fun insertAbonament(abonament:AbonamentDTO)=AbonamentDTO(
            repo.save(
                    Abonament(
                        abonament.id,
                        abonament.data_rozpoczecia,
                        abonament.data_zakonczenia,
                        abonament.sektor,
                        abonament.uwagi,
                        abonament.czy_zarchiwizowany

                    )
            )
    )

    fun deleteAbonament(id:Long) = repo.deleteById(id.toString())

    fun updateAbonament(abonamentDTO: AbonamentDTO):AbonamentDTO{
        var abonament:Abonament = repo.findById(abonamentDTO.id.toString()).get()

        abonament.data_rozpoczecia = abonamentDTO.data_rozpoczecia
        abonament.data_zakonczenia = abonamentDTO.data_zakonczenia
        abonament.sektor = abonamentDTO.sektor
        abonament.uwagi = abonamentDTO.uwagi
        abonament.czy_zarchiwizowany = abonamentDTO.czy_zarchiwizowany
        abonament.kiedy_zmodyfikowano = Date()
        abonament = repo.save(abonament)
        return AbonamentDTO(abonament)


    }


    // metody dodane poza crud repository

    fun getAbonamentByZakonczenie(zak:Date):Iterable<AbonamentDTO>{
        return repo.findByZakonczenie(zak).map{it -> AbonamentDTO(it)}
    }
}