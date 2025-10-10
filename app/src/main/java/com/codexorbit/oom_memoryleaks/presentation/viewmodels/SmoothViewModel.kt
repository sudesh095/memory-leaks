package com.codexorbit.oom_memoryleaks.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import kotlin.collections.emptyList

@OptIn(ExperimentalCoroutinesApi::class)
class SmoothViewModel : ViewModel() {
    // Create the list of items first
    private val initialList = (1..1000).map { "Item $it" }

    // Initialize MutableStateFlow with the complete list
    private val _items = MutableStateFlow(initialList)

    // The rest of your code remains the same and will now work as expected
    val processedItems = _items
        .mapLatest { list ->
            withContext(Dispatchers.Default) {
                list.map { it.reversed() }
            }
        }
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = emptyList())

}