package com.bigsteptech.deazzle.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


enum class LikeStatus(val value: Int) {
    REJECTED(0), ACCEPTED(1 ), UNDEFINED(-1)
}

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

    var likeStatus: LikeStatus = LikeStatus.UNDEFINED,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

)
