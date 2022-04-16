package org.bedu.pokedex.models

import com.google.gson.annotations.SerializedName

data class Sprices (
    @SerializedName("front_default")
    val photoUrl : String? = ""
        )