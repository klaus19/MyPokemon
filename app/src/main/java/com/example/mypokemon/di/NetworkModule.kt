package com.example.mypokemon.di

import com.example.mypokemon.network.HttpInterceptor
import com.example.mypokemon.network.PokedexClient
import com.example.mypokemon.network.PokedexService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

         return Retrofit.Builder()
             .client(okHttpClient)
             .baseUrl("https://pokeapi.co/api/v2/")
             .addConverterFactory(MoshiConverterFactory.create())
             .build()
    }

    @Provides
    @Singleton
    fun providePokedexService(retrofit: Retrofit):PokedexService{
        return  retrofit.create(PokedexService::class.java)
    }

    @Provides
    @Singleton
    fun providePokedexClient(pokedexService: PokedexService):PokedexClient{
        return PokedexClient(pokedexService)
    }
}