package com.example.mypokemon.mapper

import com.example.mypokemon.model.PokemonErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

class ErrorResponseMapper {

    /**
     * A mapper for mapping [ApiResponse.Failure.Error] response as custom [PokemonErrorResponse] instance.
     *
     * @see [ApiErrorModelMapper](https://github.com/skydoves/sandwich#apierrormodelmapper)
     */

    object ErrorResponseMapper : ApiErrorModelMapper<PokemonErrorResponse>{
        override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): PokemonErrorResponse {
            return PokemonErrorResponse(apiErrorResponse.statusCode.code,apiErrorResponse.message())
        }


    }



}