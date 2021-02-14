package com.lanic.pagingstudy.data.response


import com.google.gson.annotations.SerializedName

data class PokeiDetailResponse(
    @SerializedName("species")
    val species: Species,
    @SerializedName("sprites")
    val sprites: Sprites
) {
    data class Species(
        @SerializedName("name")
        val name: String
    )

    data class Sprites(
        @SerializedName("front_default")
        val frontDefault: String,
    )
}