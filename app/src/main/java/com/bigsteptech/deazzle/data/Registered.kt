package com.bigsteptech.deazzle.data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Registered(
    @SerialName("age")
    val age: Int? = 0,
    @SerialName("date")
    val date: String? = ""
)