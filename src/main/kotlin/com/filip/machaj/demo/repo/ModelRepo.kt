package com.filip.machaj.demo.repo

import com.filip.machaj.demo.model.dane.Model
import org.springframework.data.repository.CrudRepository

interface ModelRepo: CrudRepository<Model,Long> {
}