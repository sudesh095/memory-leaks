package com.codexorbit.oom_memoryleaks.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class JankyViewModel :  ViewModel() {
    // Create the list of items first
    private val initialList = (1..1000).map { "Item $it" }

    // Initialize MutableStateFlow with the complete list
    private val _items = MutableStateFlow(initialList)
    val items = _items
}