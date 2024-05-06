package com.atakaya.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var isFirstInit = true
    lateinit var viewModel: VM
    private var _binding: VB? = null
    val binding get() = _binding!!

    protected abstract fun initObservers()
    protected abstract fun getViewModelClass(): Class<VM>
    protected abstract fun setupViews()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (isFirstInit) {
            _binding = inflate.invoke(inflater, container, false)
            viewModel = ViewModelProvider(this)[getViewModelClass()]
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isFirstInit) {
            isFirstInit = false
            initObservers()
            setupViews()
        }
    }
}
