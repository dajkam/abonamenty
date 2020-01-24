package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.AbonamentDTO
import com.filip.machaj.demo.model.dane.Abonament
import com.filip.machaj.demo.model.dane.AbonamentInfo
import com.filip.machaj.demo.repo.AbonamentRepo
import com.filip.machaj.demo.repo.ObywatelRepo
import com.filip.machaj.demo.repo.PojazdRepo
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Service
import java.util.*

@Service("Abonament Service")

class AbonamentService {
    @Autowired
    lateinit var repo: AbonamentRepo

    @Autowired
    lateinit var repoO: ObywatelRepo

    @Autowired
    lateinit var repoP: PojazdRepo


    fun getAbonament(): Iterable<AbonamentDTO> = repo.findAll().map { it -> AbonamentDTO(it) }

    fun insertAbonament(abonament: AbonamentDTO): AbonamentDTO {

        var abonament = Abonament(
            abonament.id,
            abonament.data_rozpoczecia,
            abonament.data_zakonczenia,
            abonament.sektor,
            abonament.uwagi,
            abonament.czy_zarchiwizowany,
            Date(),
            Date(),
            repoP.findById(abonament.pojazd.id).get()

        )
        if(abonament.sektor!=abonament.uwagi)
            repo.save(abonament)

        return AbonamentDTO(repo.findLast())

}

    fun deleteAbonament(id:Long) = repo.deleteById(id)

    fun updateAbonament(abonamentDTO: AbonamentDTO):AbonamentDTO{
        var abonament:Abonament = repo.findById(abonamentDTO.id).get()

        abonament.data_rozpoczecia = abonamentDTO.data_rozpoczecia
        abonament.data_zakonczenia = abonamentDTO.data_zakonczenia
        abonament.sektor = abonamentDTO.sektor
        abonament.uwagi = abonamentDTO.uwagi
        abonament.czy_zarchiwizowany = abonamentDTO.czy_zarchiwizowany
        abonament.pojazd = repoP.findById(abonamentDTO.pojazd.id).get() // nowe
        abonament.kiedy_zmodyfikowano = Date() // zrobić żeby to pole sie zmieniało tylko gdy żeczywiście coś zmieniono
       // abonament.obywatel = repoO.findLast()// nowe obywatel  // te dwie linijki trzeba przeniesc do add abonament
       // abonament.pojazd = repoP.findLast()//nowe pojazd
        if(abonament.sektor!=abonament.uwagi)
          abonament = repo.save(abonament) /// errory przy abonament update
        return AbonamentDTO(abonament) /// trzeba poprawić update dla abonamentu



    }


    // metody dodane poza crud repository

    fun getAbonamentByZakonczenie(zak:Date):Iterable<AbonamentDTO>{
        return repo.findByZakonczenie(zak).map{it -> AbonamentDTO(it)}
    }

    fun findLastAbonament():Abonament{
        return repo.findLast()
    }
      fun findAllAbonamentInfo(): Iterable<AbonamentInfo> = repo.findAllAbonamentInfo()

        fun archiwizujAbonament(id:Long) = repo.archwizujAbonament(id)
}
