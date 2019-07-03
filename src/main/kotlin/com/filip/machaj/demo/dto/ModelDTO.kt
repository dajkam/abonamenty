package com.filip.machaj.demo.dto

import com.filip.machaj.demo.model.dane.Model
import com.filip.machaj.demo.model.dane.Pojazd

class ModelDTO(
        var nazwa:String,
        var pojazdy:Set<Pojazd> = emptySet()
) {
    var id : Long = 0

    operator fun invoke(model: Model):ModelDTO{
        return this
    }

    constructor(model: Model):this(

            model.nazwa,
            model.pojazdy

    ){
        id = model.id
    }
}