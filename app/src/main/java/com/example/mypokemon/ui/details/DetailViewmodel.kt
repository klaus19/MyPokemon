package com.example.mypokemon.ui.details

import androidx.databinding.Bindable
import com.example.mypokemon.Repository.DetailRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class DetailViewmodel @AssistedInject constructor(
    detailsRepository: DetailRepository,
    @Assisted private val pokemonName:String
):BindingViewModel() {

    @get:Bindable
    var islOADING : Boolean by bindingProperty(true)
    private set

    @get:Bindable
    var toastMessage:String? by bindingProperty(null)
    private set



}