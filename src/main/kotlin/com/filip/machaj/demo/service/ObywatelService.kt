package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.ObywatelDTO
import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.repo.ObywatelRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service("Obywatel Service")
class ObywatelService {
    

    @Autowired
    lateinit var repo : ObywatelRepo





   // fun getObywatel(): Iterable<Obywatel> = repo.findAll()

    fun  getObywatel(): Iterable<ObywatelDTO> = repo.findAll().map { it -> ObywatelDTO(it) } // do zrozumienia działania z kotlin z akcji

    //fun insertObywatel(obywatel: Obywatel):Obywatel = repo.save(obywatel)

    fun insertObywatel(obywatel: ObywatelDTO) = ObywatelDTO(
            repo.save(
                    Obywatel(
                             obywatel.id,
                             obywatel.pesel,
                             obywatel.nr_dowodu,
                             obywatel.imie,
                             obywatel.nazwisko,
                             obywatel.adres,
                             obywatel.czy_zarchiwizowany,
                             obywatel.data_urodzenia

                    )
            )
    )

    fun deleteObywatel(id:Long) = repo.deleteById(id)

   // fun updateObywatel(obywatel: Obywatel) : Obywatel = repo.save(obywatel)

    fun updateObywatel(obywatelDTO: ObywatelDTO): ObywatelDTO {
        var obywatel : Obywatel = repo.findById(obywatelDTO.id).get()

        var obywatel_old : Obywatel = repo.findById(obywatelDTO.id).get()

        obywatel.pesel = obywatelDTO.pesel
        obywatel.nr_dowodu = obywatelDTO.nr_dowodu
        obywatel.imie = obywatelDTO.imie
        obywatel.nazwisko = obywatelDTO.nazwisko
        obywatel.adres = obywatelDTO.adres
        obywatel.czy_zarchiwizowany = obywatelDTO.czy_zarchiwizowany
        obywatel.data_urodzenia = obywatelDTO.data_urodzenia
        if (obywatel.equals(obywatel_old)){
            obywatel.kiedy_zmodyfikowano = obywatel.kiedy_zmodyfikowano // wprowadzić dla pozostałych klas
        }
        else{
            obywatel.kiedy_zmodyfikowano = Date()

        }

        obywatel = repo.save(obywatel)
        return obywatelDTO(obywatel) // patrz ObywatelDTO invoke



    }
    // metody z poza crud repository

    fun getObywatelByImie(imie:String): Iterable<ObywatelDTO>{
        println(imie)
        return repo.findByImie(imie).map{it -> ObywatelDTO(it) }
    }


    fun findLastObywatel():Obywatel{
        return repo.findLast()}

    fun findByNazwisko(nazwisko:String): Iterable<ObywatelDTO>{
        return  repo.findByNazwisko(nazwisko).map { it -> ObywatelDTO(it)}

    }
}


