package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.MarkaDTO
import com.filip.machaj.demo.model.dane.Marka
import com.filip.machaj.demo.model.dane.Model
import com.filip.machaj.demo.repo.MarkaRepo
import com.filip.machaj.demo.repo.ModelRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("Marka Service")
class MarkaService {

    @Autowired
    lateinit var repo:MarkaRepo

    lateinit var repoM:ModelRepo

    fun getMarka():Iterable<MarkaDTO> = repo.findAll().map{it -> MarkaDTO(it) }

    fun insertMarka(marka: MarkaDTO) = MarkaDTO(
            repo.save(
                    Marka(
                            marka.id,
                            marka.nazwa,
                            repoM.findAll() as Set<Model>


                    )
            )
    )

    fun deleteMarka(id:Long) = repo.deleteById(id)

    fun updateMarka(markaDTO: MarkaDTO):MarkaDTO{
        var marka:Marka = repo.findById(markaDTO.id).get()

        marka.nazwa = markaDTO.nazwa
        marka.modele = markaDTO.modele
        marka = repo.save(marka)
        return markaDTO(marka)
    }
}