package com.example.mypokemon.Repository

import androidx.annotation.WorkerThread
import com.example.mypokemon.mapper.ErrorResponseMapper
import com.example.mypokemon.model.PokemonInfo
import com.example.mypokemon.network.PokedexClient
import com.example.mypokemon.persistence.PokemonDao
import com.example.mypokemon.persistence.PokemonInfoDao
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonInfoDao: PokemonInfoDao,
    private val ioDispatcher: CoroutineDispatcher
):Repository {

    @WorkerThread
    fun fetchPokemonInfo(
        name:String,
        onComplete:()-> Unit,
        onError:(String?)-> Unit
    ) = flow<PokemonInfo> {

        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
        if(pokemonInfo==null){
            /**
             * fetches a [PokemonInfo] from the network and getting [ApiResponse] asynchronously.
             * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
             */

            val response = pokedexClient.fetchPokemonInfo(name=name)
              response.suspendOnSuccess {
                  pokemonInfoDao.insertPokemonInfo(data)
                  emit(data)
              }
                  .onError {
                      /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
                      map(ErrorResponseMapper.ErrorResponseMapper) { onError("[Code: $code]: $message") }
                  }
        }

    }


}