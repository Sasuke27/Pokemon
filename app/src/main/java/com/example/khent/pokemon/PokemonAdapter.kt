package com.example.khent.pokemon

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Created by Arnold on 7 Mar 2018.
 */
class PokemonAdapter(val pokemonList: ArrayList<PokemonItems>): RecyclerView.Adapter<PokemonAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.cardview_layout, parent, false)
        return CustomViewHolder(cellForRow)  }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val pokemon: PokemonItems = pokemonList[position]
        holder!!.pokename.text = pokemon.Pokename
        val pokemonImage = holder.pokemonImg
        Picasso.with(holder.view.context).load(pokemon.sprites.front_default).into(pokemonImage)
    }

    class CustomViewHolder (val view:View): RecyclerView.ViewHolder(view){

        val pokemonImg = view.findViewById<ImageView>(R.id.pokemon_image)
        val pokename = view.findViewById<TextView>(R.id.pokemon_name)

    }

}


