package com.filip.machaj.demo.dto

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.filip.machaj.demo.model.dane.Marka
import com.filip.machaj.demo.model.dane.Model
import com.filip.machaj.demo.model.dane.Pojazd

class ModelDTO(
        var nazwa:String,
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var pojazdy:MutableList<Pojazd>,
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        var marka:Marka?
) {
    var id : Long = 0

    operator fun invoke(model: Model):ModelDTO{
        return this
    }

    constructor(model: Model):this(

            model.nazwa,
            model.pojazdy,
            model.marka

    ){
        id = model.id
    }
}