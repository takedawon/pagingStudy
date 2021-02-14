package com.lanic.pagingstudy.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lanic.pagingstudy.R
import com.lanic.pagingstudy.base.BaseActivity
import com.lanic.pagingstudy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    layoutId = R.layout.activity_main
) {

    private val viewModel by viewModels<MainViewModel>()
    private val mainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            vm = viewModel
            rycView.adapter = mainAdapter
            rycView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.getPokeiList()

        viewModel.pokeiList.observe(this, Observer {
            mainAdapter.submitList(it.results)
        })
    }
}