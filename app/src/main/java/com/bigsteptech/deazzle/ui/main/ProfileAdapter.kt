package com.bigsteptech.deazzle.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bigsteptech.deazzle.R
import com.bigsteptech.deazzle.common.clickWithAnimation
import com.bigsteptech.deazzle.common.gone
import com.bigsteptech.deazzle.common.visible
import com.bigsteptech.deazzle.data.local.LikeStatus
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.databinding.ItemProfileBinding
import java.util.*


class ProfileAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    interface ItemClickListener {
        fun onAcceptClicked(profile: Profile)
        fun onDeclineClicked(profile: Profile)
    }

    private var profileList = listOf<Profile>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context))
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int = profileList.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {

        with(holder.binding) {
            model = profileList[position]
            itemClick = clickListener

            when (profileList[position].likeStatus) {

                LikeStatus.UNDEFINED -> {
                    likeStatus.gone()
                }

                LikeStatus.REJECTED -> {
                    likeStatus.visible()
                    likeStatus.setTextColor(
                        ContextCompat.getColor(
                            likeStatus.context,
                            R.color.error
                        )
                    )
                    likeStatus.text = "Rejected"
                }

                else -> {
                    likeStatus.visible()
                    likeStatus.setTextColor(
                        ContextCompat.getColor(
                            likeStatus.context,
                            R.color.success
                        )
                    )
                    likeStatus.text = "Accepted"
                }
            }

            acceptButton.setOnClickListener {
                (it as ImageView).clickWithAnimation(clickListener, profileList[position], true)
            }

            rejectButton.setOnClickListener {
                (it as ImageView).clickWithAnimation(clickListener, profileList[position], false)
            }

        }

        holder.binding.executePendingBindings()

    }

    fun set(profileList: List<Profile>) {
        this.profileList = profileList
        notifyDataSetChanged()
    }

    class ProfileViewHolder(val binding: ItemProfileBinding) : RecyclerView.ViewHolder(binding.root)

}