package com.filip.machaj.demo.dto

import com.filip.machaj.demo.model.dane.Marka
import com.filip.machaj.demo.model.dane.Model

data class MarkaDTO(
        var nazwa:String,
        var modele: MutableList<Model>

) {
    var id: Long = 0

    operator fun invoke(marka:Marka):MarkaDTO{
        return this
    }

    constructor(marka: Marka):this(
            marka.nazwa,
            marka.modele
    ){
        id = marka.id
    }
}