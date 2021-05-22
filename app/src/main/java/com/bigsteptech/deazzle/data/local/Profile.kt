package com.bigsteptech.deazzle.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "profile")
data class Profile(

    var cellPhone: String? = "",

    var age: Int? = 0,

    var email: String? = "",

    var gender: String? = "",

    var remoteId: String? = "",

    var city: String? = "",

    var country: String? = "",

    var name: String? = "",

    var phone: String? = "",

    var picture: String? = "",

    var likeStatus : Int? = 0,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

)
