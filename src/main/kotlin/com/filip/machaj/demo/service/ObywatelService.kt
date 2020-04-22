package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.ObywatelDTO
import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.repo.ObywatelRepo
import com.filip.machaj.demo.service.exceptions.ModifyingArchivedObjectExcepion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service("Obywatel Service")
class ObywatelService {
    

    @Autowired
    lateinit var repo : ObywatelRepo





   // fun getObywatel(): Iterable<Obywatel> = repo.findAll()

    fun  getObywatel(): Iterable<ObywatelDTO> = repo.findAll().map { it -> ObywatelDTO(it) } // do zrozumienia działania z kotlin z akcji

    //fun insertObywatel(obywatel: Obywatel):Obywatel = repo.save(obywatel)

    fun insertObywatel(obywatel: ObywatelDTO) : ObywatelDTO{


        var obywatel =  Obywatel(
                obywatel.id,
                obywatel.pesel,
                obywatel.nr_dowodu,
                obywatel.imie,
                obywatel.nazwisko,
                obywatel.adres,
                obywatel.czy_zarchiwizowany,
                obywatel.data_urodzenia,
                Date(),
                Date(),
                obywatel.pojazdy

        )
        if(obywatel.imie!=obywatel.pesel)
             repo.save(obywatel)


     return    ObywatelDTO(repo.findLast())
}

    fun deleteObywatel(id:Long) = repo.deleteById(id)

   // fun updateObywatel(obywatel: Obywatel) : Obywatel = repo.save(obywatel)

    fun updateObywatel(obywatelDTO: ObywatelDTO): ObywatelDTO {
        var obywatel : Obywatel = repo.findById(obywatelDTO.id).get()

        if((obywatel.czy_zarchiwizowany == true)&&(obywatelDTO.czy_zarchiwizowany == true))
            throw ModifyingArchivedObjectExcepion("Ten obywatel został zarchiwizowany " + // napisz testy do tego wyjątku
                    "i przez to nie może być modyfikowany")

        val obywatel_old : Obywatel = repo.findById(obywatelDTO.id).get()

        obywatel.pesel = obywatelDTO.pesel
        obywatel.nr_dowodu = obywatelDTO.nr_dowodu
        obywatel.imie = obywatelDTO.imie
        obywatel.nazwisko = obywatelDTO.nazwisko
        obywatel.adres = obywatelDTO.adres
        obywatel.czy_zarchiwizowany = obywatelDTO.czy_zarchiwizowany
        obywatel.data_urodzenia = obywatelDTO.data_urodzenia
        obywatel.pojazdy = obywatelDTO.pojazdy // sprawdzić czy w liscie jest tylko 1 niezarchiwizowany pojazd
        if (obywatel.equals(obywatel_old)){
            obywatel.kiedy_zmodyfikowano = obywatel.kiedy_zmodyfikowano // wprowadzić dla pozostałych klas
        }
        else{
            obywatel.kiedy_zmodyfikowano = Date()

        }
        if(obywatel.imie!=obywatel.pesel)
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

    @Transactional
    fun archiwizujObywatela(id:Long) = repo.archwizujObywatela(id)

    @Transactional
    fun odnowObywatela(id:Long) = repo.odnowObywatela(id)


    fun szukaj(fraza:String):Iterable<Obywatel> = repo.szukaj(fraza.toLowerCase())

}


