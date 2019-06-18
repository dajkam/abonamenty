package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.PojazdDTO
import com.filip.machaj.demo.model.dane.Pojazd
import com.filip.machaj.demo.repo.PojazdRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service("Pojazd Service")
class PojazdService {

    @Autowired
    lateinit var repo : PojazdRepo

    fun getPojazd():Iterable<PojazdDTO> = repo.findAll().map {it -> PojazdDTO(it) }


    fun insertPojazd(pojazd: PojazdDTO) = PojazdDTO(
            repo.save(
                    Pojazd(
                            pojazd.id,
                            pojazd.model,
                            pojazd.kolor,
                            pojazd.uwagi,
                            pojazd.nr_rejstracyjny_pojazdu,
                            pojazd.rok_produkcji,
                            pojazd.czy_zarchiwizowany

                    )
            )
    )

    fun deletePojazd(id:Long) = repo.deleteById(id)

    fun updatePojazd(pojazdDTO: PojazdDTO):PojazdDTO{
        var pojazd:Pojazd = repo.findById(pojazdDTO.id).get()

        pojazd.model = pojazdDTO.model
        pojazd.kolor = pojazdDTO.kolor
        pojazd.uwagi = pojazdDTO.uwagi
        pojazd.nr_rejstracyjny_pojazdu = pojazdDTO.nr_rejstracyjny_pojazdu
        pojazd.rok_producji = pojazdDTO.rok_produkcji
        pojazd.czy_zarchiwizowany = pojazdDTO.czy_zarchiwizowany
        pojazd.kiedy_zmodyfikowano = Date()
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
}