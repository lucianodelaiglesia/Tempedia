package com.ldelaiglesia.tempedia.presentation.temtem_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldelaiglesia.tempedia.common.Resource
import com.ldelaiglesia.tempedia.domain.models.Temtem
import com.ldelaiglesia.tempedia.domain.models.Type
import com.ldelaiglesia.tempedia.domain.usecases.GetAllTypesUseCase
import com.ldelaiglesia.tempedia.domain.usecases.GetTemtemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemtemListViewModel @Inject constructor(
    private val getTemtemUseCase: GetTemtemUseCase,
    private val getAllTypesUseCase: GetAllTypesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TemtemListState())
    val state: State<TemtemListState> = _state

    private var originalTemtemList = emptyList<Temtem>()

    private val _openDrawerEvent = MutableSharedFlow<Unit>()
    val openDrawerEvent: SharedFlow<Unit> = _openDrawerEvent

    private val _typeList = mutableStateOf(emptyList<Type>())
    val typeList: State<List<Type>> = _typeList

    init {
        getTemtemList()
        getTypes()
    }

    private fun getTemtemList() {
        getTemtemUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    originalTemtemList = result.data ?: emptyList()
                    _state.value =
                        TemtemListState(temtemList = originalTemtemList)
                }

                is Resource.Loading -> _state.value = TemtemListState(isLoading = true)
                is Resource.Error -> _state.value =
                    TemtemListState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }

    fun searchTemtem(query: String) {
        viewModelScope.launch {
            val filteredList = if (query.isBlank()) {
                originalTemtemList
            } else {
                originalTemtemList.filter { temtem ->
                    temtem.name.contains(query, ignoreCase = true)
                }
            }
            _state.value = state.value.copy(temtemList = filteredList)
        }
    }

    fun resetSearch() {
        viewModelScope.launch {
            _state.value = state.value.copy(temtemList = originalTemtemList)
        }
    }

    private fun getTypes() {
        getAllTypesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _typeList.value = result.data?.map { it } ?: emptyList()
                }

                is Resource.Loading -> {
                    _state.value = TemtemListState(isLoading = true)
                }

                is Resource.Error -> {
                    TemtemListState(error = result.message ?: "Unknown error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun openDrawer(){
        viewModelScope.launch {
            _openDrawerEvent.emit(Unit)
        }
    }

    fun filterByTypes(types: Set<String>) {
        viewModelScope.launch {
            val filteredList = if (types.isEmpty()) {
                originalTemtemList
            } else {
                originalTemtemList.filter { temtem ->
                    temtem.types.any { type -> types.contains(type) }
                }
            }
            _state.value = state.value.copy(temtemList = filteredList)
        }
    }
}