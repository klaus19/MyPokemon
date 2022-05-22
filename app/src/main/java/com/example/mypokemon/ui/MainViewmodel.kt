package com.example.mypokemon.ui

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.mypokemon.Repository.MainRepository
import com.example.mypokemon.model.Pokemon
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

class MainViewmodel @Inject constructor(
    private val mainRepository: MainRepository
): BindingViewModel() {

    @get:Bindable
    var islOADING: Boolean by bindingProperty(false)
    private set

    @get:Bindable
    var toastmessage:String? by bindingProperty(null)
    private set

    private val pokemonFetchingIndex:MutableStateFlow<Int> = MutableStateFlow(0)
    private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page->
        mainRepository.fetchPokemonList(
            page=page,
            onStart = {islOADING=true},
            onComplete = {islOADING=false},
            onError = {toastmessage=it}
        )
    }

    @get:Bindable
    val pokemonList:List<Pokemon> by pokemonListFlow.asBindingProperty(viewModelScope, emptyList())

    init {
        Timber.d("init ViewModel")
    }

    @MainThread
    fun fetchNextPokemonList() {
        if (!islOADING) {
            pokemonFetchingIndex.value++
        }
    }
}