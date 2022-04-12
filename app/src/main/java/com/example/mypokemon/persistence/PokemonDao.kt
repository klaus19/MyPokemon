package com.example.mypokemon.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypokemon.model.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonlist:List<Pokemon>)

    @Query("SELECT * FROM Pokemon WHERE page = :page_")
    suspend fun getPokemonList(page_:Int):List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE page <= :page_")
    suspend fun getAllPokemonList(page_:Int):List<Pokemon>


}