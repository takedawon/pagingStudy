package com.lanic.pagingstudy.data.response


import com.google.gson.annotations.SerializedName

data class PokeiResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}