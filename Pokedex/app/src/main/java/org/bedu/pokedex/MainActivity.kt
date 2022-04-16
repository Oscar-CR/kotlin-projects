package org.bedu.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import com.squareup.picasso.Picasso
import org.bedu.pokedex.databinding.ActivityMainBinding
import org.bedu.pokedex.models.Pokemon
import org.bedu.pokedex.retrofit.webService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val baseUrl="https://pokeapi.co/api/v2/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            searchPokemon()
        }
    }

    private fun searchPokemon(){
        val pokename = binding.etPokemon.text.toString()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonServices = retrofit.create(webService::class.java)
        val call = pokemonServices.getPokemon(pokename)

        call?.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                val pokemon = response.body()

                binding.tvPokemon.text = pokemon?.name
                binding.tvWeight.text = "peso ${pokemon?.weight}"
                Picasso.get().load(pokemon?.sprice?.photoUrl).into(binding.pokemon)


            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {

            }

        })

    }
}