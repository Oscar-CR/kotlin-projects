package org.bedu.pokedex.retrofit

import org.bedu.pokedex.models.Pokemon
import org.bedu.pokedex.models.Type
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface webService {
    // Definimos el endpoint pokemont/{pokemon}, siendo este último una variable a ingresar por el usuario (en este caso, desde el edit text)
    @GET("pokemon/{pokemon}/")
    fun getPokemon(@Path("pokemon") pokemon :String):Call<Pokemon> //la clase Pokemon dentro de Call indica que el json de respuesta va a   ser transformado es un objeto de esa clase.

    @GET("type/{id}") //tipos de pokemon, se obtienen por id
    fun getType(@Path("id") id: Int): Call<List<Type>>
}