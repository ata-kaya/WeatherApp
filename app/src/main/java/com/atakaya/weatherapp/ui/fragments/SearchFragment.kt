package com.atakaya.weatherapp.ui.fragments

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.atakaya.weatherapp.data.remote.api.ApiResult
import com.atakaya.weatherapp.databinding.FragmentSearchBinding
import com.atakaya.weatherapp.ui.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
    FragmentSearchBinding::inflate
) {
    override fun initObservers() {

    }

    override fun getViewModelClass() = SearchViewModel::class.java

    override fun setupViews() {
        binding.buttonSecond.setOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.currentWeather1.observe(this) {
            when (it) {
                is ApiResult.Success -> {
                    binding.textviewSecond.text = "second ${it.data?.location?.name}"
                    Toast.makeText(context, "search this is success", Toast.LENGTH_SHORT).show()
                }

                is ApiResult.Error -> {
                    Toast.makeText(context, "search this is error", Toast.LENGTH_SHORT).show()
                }

                is ApiResult.Loading -> {
                    Toast.makeText(context, "search this is loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
