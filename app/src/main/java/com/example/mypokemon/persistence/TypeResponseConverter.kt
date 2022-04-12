package com.example.mypokemon.persistence

import androidx.room.ProvidedTypeConverter
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
){

}