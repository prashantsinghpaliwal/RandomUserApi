package com.bigsteptech.deazzle.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bigsteptech.deazzle.R
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.databinding.ItemProfileBinding
import com.jakewharton.rxbinding4.view.visibility


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

                -1 -> {
                    likeStatus.visibility = View.GONE
                }

                0 -> {
                    likeStatus.visibility = View.VISIBLE
                    likeStatus.setTextColor(
                        ContextCompat.getColor(
                            likeStatus.context,
                            R.color.error
                        )
                    )
                    likeStatus.text = "Rejected"
                }

                else -> {
                    likeStatus.visibility = View.VISIBLE
                    likeStatus.setTextColor(
                        ContextCompat.getColor(
                            likeStatus.context,
                            R.color.success
                        )
                    )
                    likeStatus.text = "Accepted"
                }
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