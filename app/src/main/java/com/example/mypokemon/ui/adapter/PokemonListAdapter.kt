package com.example.mypokemon.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mypokemon.R
import com.example.mypokemon.model.Pokemon
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class PokemonListAdapter:BindingListAdapter<Pokemon,PokemonListAdapter.PokemonViewHolder>(diffUtil) {

    private var onClickAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        parent.binding<ItemPokemonBinding>(R.layout.item_pokemon).let(::PokemonViewHolder)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) =
        holder.bindPokemon(getItem(position))


    companion object{

        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>(){
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem

        }
    }




}