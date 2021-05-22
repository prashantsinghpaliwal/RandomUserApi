package com.bigsteptech.deazzle.data.remote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponse(

    @SerialName("info")
    val info: Info? = Info(),

    @SerialName("results")
    val results: List<Result>? = listOf()

)