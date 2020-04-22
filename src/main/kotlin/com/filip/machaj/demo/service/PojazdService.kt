package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.PojazdDTO
import com.filip.machaj.demo.model.dane.PojModMar
import com.filip.machaj.demo.model.dane.Pojazd
import com.filip.machaj.demo.repo.ModelRepo
import com.filip.machaj.demo.repo.ObywatelRepo
import com.filip.machaj.demo.repo.PojazdRepo
import com.filip.machaj.demo.service.exceptions.ModifyingArchivedObjectExcepion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service("Pojazd Service")
class PojazdService {

    @Autowired
    lateinit var repo : PojazdRepo

    @Autowired
    lateinit var repoM: ModelRepo

    @Autowired
    lateinit var repoO : ObywatelRepo





    fun getPojazd():Iterable<PojazdDTO> = repo.findAll().map {it -> PojazdDTO(it) }


    fun insertPojazd(pojazd: PojazdDTO) = PojazdDTO(
            repo.save(
                    Pojazd(
                            pojazd.id,
                            pojazd.kolor,
                            pojazd.uwagi,
                            pojazd.nr_rejstracyjny_pojazdu,
                            pojazd.rok_produkcji,
                            pojazd.czy_zarchiwizowany,
                            //repoM.findById(1).get(),
                            repoM.findById(pojazd.model.id).get(),
                            repoO.findById(pojazd.obywatel.id).get(),
                            pojazd.abonamenty



                    )
            )
    )

    fun deletePojazd(id:Long) = repo.deleteById(id)

    fun updatePojazd(pojazdDTO: PojazdDTO):PojazdDTO{
        var pojazd:Pojazd = repo.findById(pojazdDTO.id).get()

        if((pojazd.czy_zarchiwizowany == true)&&(pojazdDTO.czy_zarchiwizowany == true))
            throw ModifyingArchivedObjectExcepion("Ten pojazd został zarchiwizowany " + // napisz testy do tego wyjątku
                    "i przez to nie może być modyfikowany")

        var pojazd_old: Pojazd = repo.findById(pojazdDTO.id).get()
        pojazd.kolor = pojazdDTO.kolor
        pojazd.uwagi = pojazdDTO.uwagi
        pojazd.nr_rejstracyjny_pojazdu = pojazdDTO.nr_rejstracyjny_pojazdu
        pojazd.rok_produkcji = pojazdDTO.rok_produkcji
        pojazd.czy_zarchiwizowany = pojazdDTO.czy_zarchiwizowany
        pojazd.kiedy_zmodyfikowano = Date() // zrobić żeby to pole sie zmieniało tylko gdy żeczywiście coś zmieniono
        pojazd.model = repoM.findById(pojazdDTO.model.id).get()
        pojazd.obywatel = repoO.findById(pojazdDTO.obywatel.id).get()
        pojazd.abonamenty = pojazdDTO.abonamenty
        if(pojazd == pojazd_old){ // nie działa dla pojazdu i abonamentu
            pojazd.kiedy_zmodyfikowano = pojazd.kiedy_zmodyfikowano
        }
        else{
            pojazd.kiedy_zmodyfikowano = Date()
        }

        pojazd = repo.save(pojazd)
        return  pojazdDTO(pojazd)

    }

    // metody dodane poza crud repository

    fun getPojazdByModel(model:String): Iterable<PojazdDTO>{
        return repo.findPojazdByModel(model).map{it -> PojazdDTO(it)}
    }

    fun findLastPojazd():Pojazd{
        return repo.findLast()
    }

    fun getAllPojModMar():Iterable<PojModMar> = repo.findAllPojModMar()
    @Transactional
    fun archwizujPojazd(id:Long) = repo.archwizujPojazd(id)

    @Transactional
    fun odnowPojazd(id:Long) = repo.odnowPojazd(id)

    fun szukaj(fraza:String):Iterable<PojModMar> = repo.szukaj(fraza.toLowerCase())
}
