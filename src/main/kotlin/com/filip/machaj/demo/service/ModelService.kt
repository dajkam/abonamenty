package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.ModelDTO
import com.filip.machaj.demo.model.dane.ModMar
import com.filip.machaj.demo.model.dane.Model
import com.filip.machaj.demo.model.dane.Pojazd
import com.filip.machaj.demo.repo.MarkaRepo
import com.filip.machaj.demo.repo.ModelRepo
import com.filip.machaj.demo.repo.PojazdRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service("Model Service")
class ModelService {

    @Autowired
    lateinit var repo : ModelRepo

    @Autowired
    lateinit var repoP : PojazdRepo

    @Autowired
    lateinit var repoM:MarkaRepo

    fun getModel():Iterable<ModelDTO> = repo.findAll().map{ it -> ModelDTO(it)}

    fun insertModel(model: ModelDTO) = ModelDTO(
            repo.save(
                    Model(
                            model.id,
                            model.nazwa,
                            repoP.findAll() as MutableList<Pojazd>,
                           // repoM.findById(1).get()
                            repoM.findById(model.marka.id).get()


                    )
            )
    )

    fun deleteModel(id:Int) = repo.deleteById(id)

    fun updateModel(modelDTO: ModelDTO):ModelDTO{
        var model:Model = repo.findById(modelDTO.id).get()

        model.nazwa = modelDTO.nazwa
        model.pojazdy = modelDTO.pojazdy
        model = repo.save(model)
        return modelDTO(model)
    }

    fun findLastModel():Model{
        return repo.findLast()
    }
    fun getAllModMar():Iterable<ModMar> = repo.getAllModMar()
}
