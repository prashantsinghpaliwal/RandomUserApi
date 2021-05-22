package com.bigsteptech.deazzle.common

import android.view.View
import android.widget.ImageView
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.ui.main.ProfileAdapter


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


fun ImageView.clickWithAnimation(
    clickListener: ProfileAdapter.ItemClickListener,
    profile: Profile,
    acceptRequest: Boolean
) {
    animate()
        .scaleX(.8f)
        .scaleY(.8f)
        .setDuration(150L)
        .withEndAction {
            this.scaleX = 1f
            this.scaleY = 1f
            if (acceptRequest)
                clickListener.onAcceptClicked(profile)
            else clickListener.onDeclineClicked(profile)
        }
        .start()
}





