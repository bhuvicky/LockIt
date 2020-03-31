package com.bhuvanesh.appbase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/*
* The ViewModelProviders.of() method internally creates default ViewModelProvider.Factory implementation for creating our ViewModel with no argument.
* So when you add argument in the constructor, the inner implementation of ViewModelProvider.Factory failed to initialize your ViewModel because
* ViewModelProvider.Factory call the primary constructor for creating the ViewModel’s instance.
*
* If you add argument in constructor you have to create your own implementation of ViewModelProvider.Factory to create your ViewModel instance.
*
* When to use ViewModelProvider.Factory ?
* If your ViewModel have dependencies then you should pass this dependencies through the constructor (It is the best way to pass your dependencies),
* so you can mock that dependencies and test your ViewModel.
*
* */
class AppViewModelFactory<T>(val create: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return create() as T
    }
}