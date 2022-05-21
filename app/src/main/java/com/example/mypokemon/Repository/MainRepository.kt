package com.example.mypokemon.Repository

import com.example.mypokemon.network.PokedexClient
import com.example.mypokemon.persistence.PokemonDao
import kotlinx.coroutines.CoroutineDispatcher

class MainRepository(
    pokedexClient: PokedexClient,
    pokemonDao: PokemonDao,
    coroutineDispatcher: CoroutineDispatcher
) {
}