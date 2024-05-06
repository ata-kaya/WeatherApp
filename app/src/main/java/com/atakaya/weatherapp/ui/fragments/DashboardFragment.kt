package com.atakaya.weatherapp.ui.fragments

import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.atakaya.weatherapp.MainApplication
import com.atakaya.weatherapp.R
import com.atakaya.weatherapp.data.remote.api.ApiResult
import com.atakaya.weatherapp.databinding.FragmentDashboardBinding
import com.atakaya.weatherapp.ui.adapters.WeatherAdapter
import com.atakaya.weatherapp.ui.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(
    FragmentDashboardBinding::inflate
) {
    override fun initObservers() {
        viewModel.getAllWeathers()
    }

    override fun getViewModelClass() = DashboardViewModel::class.java

    @Inject
    lateinit var mainApplication: MainApplication
    override fun setupViews() {
        binding.menuButton.setOnClickListener {
            viewModel.getCurrentWeather()
        }
        binding.addButton.setOnClickListener {
            val options = NavOptions.Builder()
                .setPopUpTo(R.id.FirstFragment, false)
                .build()
            findNavController().navigate(R.id.SecondFragment, null, options)
        }
        viewModel.allWeathers.observe(this) {
            when (it) {
                is ApiResult.Success -> {
                    Toast.makeText(context, "dashboard this is success", Toast.LENGTH_SHORT).show()
                    val lp = LinearLayoutManager(
                        context
                    )
                    lp.orientation = LinearLayoutManager.VERTICAL
                    val dividerItemDecoration = DividerItemDecoration(
                        context,
                        lp.orientation
                    )
                    dividerItemDecoration.setDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.devider,
                            null
                        )!!
                    )

                    binding.rvWeather.apply {
                        isNestedScrollingEnabled = true
                        adapter = WeatherAdapter(
                            ArrayList(it.data),
                            mainApplication
                        )
                        setHasFixedSize(false)
                        layoutManager = lp
                        addItemDecoration(
                            dividerItemDecoration
                        )
                    }
                }

                is ApiResult.Error -> {
                    Toast.makeText(context, "dashboard this is error", Toast.LENGTH_SHORT).show()
                }

                is ApiResult.Loading -> {
                    Toast.makeText(context, "dashboard this is loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
