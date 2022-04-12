package com.example.mypokemon.persistence

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypokemon.model.PokemonInfo

interface PokemonInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfo(pokemonInfo: PokemonInfo)

    @Query("SELECT * FROM PokemonInfo WHERE name = :name_")
    suspend fun getPokemonInfo(name_: String): PokemonInfo?
}