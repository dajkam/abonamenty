package com.filip.machaj.demo.service

import com.filip.machaj.demo.model.dane.ForeignKeySetter
import com.filip.machaj.demo.repo.MarkaRepo
import com.filip.machaj.demo.repo.ModelRepo
import com.filip.machaj.demo.repo.PojazdRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service("ForeignKeySetter Service")
class ForeignKeySetterService {

    @Autowired
    lateinit var repoMarka: MarkaRepo

    @Autowired
    lateinit var repoModel: ModelRepo

    @Autowired
    lateinit var repoPojazd: PojazdRepo


    fun setForeignKeys(foreignKeySetter: ForeignKeySetter):ForeignKeySetter{

      /*  repoMarka.
                findById(foreignKeySetter.marka_id).get()
                .modele.
                add(repoModel.findById(foreignKeySetter.model_id).get())

        repoModel.findById(foreignKeySetter.model_id).get().
                pojazdy.
                add(repoPojazd.findById(foreignKeySetter.pojazd_id).get())

        repoModel.findById(foreignKeySetter.model_id).get().
                marka=repoMarka.findById(foreignKeySetter.marka_id).get()

       // println(repoModel.findById(foreignKeySetter.model_id).get().marka.toString())

        repoPojazd.findById(foreignKeySetter.pojazd_id).get().
                model=repoModel.findById(foreignKeySetter.model_id).get()*/


        repoMarka.
                findById(1).get()
                .modele.
                add(repoModel.findById(1).get())

        repoModel.findById(1).get().
                pojazdy.
                add(repoPojazd.findById(1).get())

        repoModel.findById(1).get().
                marka=repoMarka.findById(1).get()

        // println(repoModel.findById(foreignKeySetter.model_id).get().marka.toString())

        repoPojazd.findById(1).get().
                model=repoModel.findById(foreignKeySetter.model_id).get()

        return foreignKeySetter
    }
}