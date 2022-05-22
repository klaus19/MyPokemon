package com.example.mypokemon.bindings

import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.whatif.whatIfNotNullAs

object RecyclerviewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view:RecyclerView,adapter: RecyclerView.Adapter<*>){
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view:RecyclerView,itemList: List<Any>){
        view.adapter.whatIfNotNullAs<BindingListAdapter<Any,*>> {
            adaper ->adaper.submitList(itemList)
        }
    }

    @JvmStatic
    @BindingAdapter("paginationPokemonList")
    fun paginationPokemonList(view:RecyclerView,viewModel: ViewModel){

    }

}