package com.bigsteptech.deazzle.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bigsteptech.deazzle.R
import com.bigsteptech.deazzle.common.Status
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.data.remote.MainResponse
import com.bigsteptech.deazzle.databinding.ActivityMainBinding
import com.bigsteptech.deazzle.ui.custom.SnapHelper
import dagger.hilt.android.AndroidEntryPoint
import java.security.acl.Owner

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ProfileAdapter.ItemClickListener {

    private val viewModel by viewModels<MainViewModel>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val profileList = mutableListOf<Profile>()
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi()
        setUpObservers()
    }

    private fun setUpUi() {

        with(binding) {

            profileAdapter = ProfileAdapter(this@MainActivity)
            profileRecyclerView.apply {
                this.adapter = profileAdapter
            }

//            SnapHelper().attachToRecyclerView(profileRecyclerView)

        }

    }

    private fun setUpObservers() {

        viewModel.data.observe(this, {

            it?.let { data ->

                if (data is MainResponse) {

                    profileAdapter.set(data.results?.map {
                        Profile(
                            it.cell!!, it.dob?.age!!, it.email!!, it.gender!!, it.id?.value,
                            it.location?.city, it.location?.country, "${it.name?.title} " +
                                    "${it.name?.first} " +
                                    "${it.name?.last}", it.phone, it.picture?.large,
                                -1
                        )
                    }?.toList()!! as MutableList<Profile>)

                } else
                    profileAdapter.set(data as MutableList<Profile>)
            }

        })

        viewModel.getCachedProfiles().observe(this, {
            it?.let {
                profileAdapter.set(it)
            }
        })
    }

    override fun onAcceptClicked(profile: Profile) {
        viewModel.updateStatus(profile,1)
    }

    override fun onDeclineClicked(profile: Profile) {
        viewModel.updateStatus(profile,0)
    }
}