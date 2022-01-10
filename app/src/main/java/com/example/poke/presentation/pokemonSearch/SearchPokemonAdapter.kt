package com.example.poke.presentation.pokemonSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.poke.databinding.ItemFilmBinding
import com.example.poke.databinding.ItemPokemonBinding
import com.example.poke.domain.entity.Film
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class SearchPokemonAdapter(
    private val onPokemonClicked: (Pokemon) -> Unit
) : ListAdapter<Pokemon, SearchPokemonAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem
    }
) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with (holder.binding) {
            val item = getItem(position)
            itemPokemonName.text = item.name
            root.setOnClickListener { onPokemonClicked(item) }
        }
    }

    class ViewHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root)

}