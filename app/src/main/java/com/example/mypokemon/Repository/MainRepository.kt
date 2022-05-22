package com.example.mypokemon.Repository

import androidx.annotation.WorkerThread
import com.example.mypokemon.mapper.ErrorResponseMapper
import com.example.mypokemon.model.Pokemon
import com.example.mypokemon.network.PokedexClient
import com.example.mypokemon.persistence.PokemonDao
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MainRepository(
    private val pokedexClient: PokedexClient,
    private val pokemonDao: PokemonDao,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var pokemons = pokemonDao.getPokemonList(page)
        if (pokemons.isEmpty()) {
            /**
             * fetches a list of [Pokemon] from the network and getting [ApiResponse] asynchronously.
             * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
             */
            val response = pokedexClient.fetchPokemonList(page = page)
            response.suspendOnSuccess {
                pokemons = data.results
                pokemons.forEach { pokemon -> pokemon.page = page }
                pokemonDao.insertPokemonList(pokemons)
                emit(pokemonDao.getAllPokemonList(page))
            }
                // handles the case when the API request gets an error response.
                // e.g., internal server error.
                .onError {
                    /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
                    map(ErrorResponseMapper.ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                // handles the case when the API request gets an exception response.
                // e.g., network connection error.
                .onException {
                    onError(message)
                }
        }
        else {
            emit(pokemonDao.getAllPokemonList(page))
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
            }
