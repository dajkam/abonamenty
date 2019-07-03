package com.filip.machaj.demo.service

import com.filip.machaj.demo.dto.ModelDTO
import com.filip.machaj.demo.model.dane.Model
import com.filip.machaj.demo.model.dane.Pojazd
import com.filip.machaj.demo.repo.ModelRepo
import com.filip.machaj.demo.repo.PojazdRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service("Model Service")
class ModelService {

    @Autowired
    lateinit var repo : ModelRepo

    lateinit var repoP : PojazdRepo

    fun getModel():Iterable<ModelDTO> = repo.findAll().map{ it -> ModelDTO(it)}

    fun insertModel(model: ModelDTO) = ModelDTO(
            repo.save(
                    Model(
                            model.id,
                            model.nazwa,
                            repoP.findAll() as Set<Pojazd>
                    )
            )
    )

    fun deleteModel(id:Long) = repo.deleteById(id)

    fun updateModel(modelDTO: ModelDTO):ModelDTO{
        var model:Model = repo.findById(modelDTO.id).get()

        model.nazwa = modelDTO.nazwa
        model.pojazdy = modelDTO.pojazdy
        model = repo.save(model)
        return modelDTO(model)
    }
}