package com.example.poke.presentation.pokemonFavorite

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.poke.databinding.ItemPokemonBinding
import com.example.poke.domain.entity.Pokemon
import kotlin.reflect.KFunction1

class PokemonHorizontalAdapter(
    private val onPokemonClicked: KFunction1<Pokemon, Unit>,
) : ListAdapter<Pokemon, PokemonHorizontalAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem
    }
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val item = getItem(position)
            var color = Color.parseColor("#aa${"%06x".format(item.color)}")
            itemPokemonName.text = item.name
            itemPokemonIdSubject.text = item.id.toString()
            itemSelf.setBackgroundColor(color)
            root.setOnClickListener { onPokemonClicked(item) }
        }
    }

    class ViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)

}
