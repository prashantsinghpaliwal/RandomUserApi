package com.bigsteptech.deazzle.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bigsteptech.deazzle.R
import com.bigsteptech.deazzle.data.local.LikeStatus
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ProfileAdapter.ItemClickListener {

    private val viewModel by viewModels<MainViewModel>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

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
        viewModel.getCachedProfiles().observe(this, {
            profileAdapter.set(it)
        })

        viewModel.errorData.observe(this, {
            it?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        })
    }

    override fun onAcceptClicked(profile: Profile) {
        viewModel.updateStatus(profile, LikeStatus.ACCEPTED)
    }

    override fun onDeclineClicked(profile: Profile) {
        viewModel.updateStatus(profile, LikeStatus.REJECTED)
    }
}