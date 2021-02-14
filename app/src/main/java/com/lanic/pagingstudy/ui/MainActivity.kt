package com.lanic.pagingstudy.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.lanic.pagingstudy.R
import com.lanic.pagingstudy.base.BaseActivity
import com.lanic.pagingstudy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    layoutId = R.layout.activity_main
) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            vm = viewModel


        }
    }
}