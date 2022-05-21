package com.example.mypokemon.di

import com.example.mypokemon.Repository.DetailRepository
import com.example.mypokemon.Repository.MainRepository
import com.example.mypokemon.network.PokedexClient
import com.example.mypokemon.persistence.PokemonDao
import com.example.mypokemon.persistence.PokemonInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        pokedexClient: PokedexClient,
        pokemonDao: PokemonDao,
        coroutineDispatcher: CoroutineDispatcher
    ):MainRepository{
        return MainRepository(pokedexClient, pokemonDao, coroutineDispatcher)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailedRepository(
        pokedexClient: PokedexClient,
        pokemonInfoDao: PokemonInfoDao,
        coroutineDispatcher: CoroutineDispatcher
    ):DetailRepository{
        return DetailRepository(pokedexClient,pokemonInfoDao,coroutineDispatcher)
    }


}