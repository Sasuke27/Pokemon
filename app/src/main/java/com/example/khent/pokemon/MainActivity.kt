package com.example.khent.pokemon

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.widget.ProgressBar
import android.widget.TextView
import org.json.JSONObject
import retrofit2.Retrofit
import java.net.URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    internal lateinit var recyclerView: RecyclerView
    private lateinit var retrofit: Retrofit
    private lateinit var progressBar: ProgressBar
    private lateinit var textPokemon: TextView

    private var pokemonList = ArrayList<PokemonItems>()
    private val url = "https://pokeapi.co/api/v2/pokemon/"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        for (i in 1 ..20)
        {
            doAsync{
                val resultJson =  URL(url + i).readText()
                uiThread {
                    val jsonObj = JSONObject(resultJson)

                    val id = jsonObj.getInt("id")
                    val pname = jsonObj.getString("name")
                    val sprites = jsonObj.getString("sprites")
                    val jsonObj2 = JSONObject(sprites)
                    val front_default = jsonObj2.getString("front_default")

                    pokemonList.add(PokemonItems(id, pname, Sprites(front_default)))

                    recyclerView.adapter = PokemonAdapter(pokemonList)
                    if(pokemonList.size!=0)
                    {
                        textPokemon.text = "You have "+pokemonList.size.toString() + " Pokemons"

                    }
                    if(pokemonList.size == 20){
                        progressBar.visibility = GONE
                    }
                }
            }
        }


    }

}
