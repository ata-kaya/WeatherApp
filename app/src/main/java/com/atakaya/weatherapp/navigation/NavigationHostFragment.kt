package com.atakaya.weatherapp.navigation

import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment

class NavigationHostFragment : NavHostFragment() {

    override fun onCreateNavHostController(navHostController: NavHostController) {
        super.onCreateNavHostController(navHostController)
        navHostController.addOnDestinationChangedListener { _, destination, arguments ->

        }
    }
}
