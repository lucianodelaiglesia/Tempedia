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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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

    private var temtemList = emptyList<Temtem>()

    private val _openDrawerEvent = MutableSharedFlow<Unit>()
    val openDrawerEvent: SharedFlow<Unit> = _openDrawerEvent

    private val _typeList = mutableStateOf(emptyList<Type>())
    val typeList: State<List<Type>> = _typeList

    private val _selectedTypes = MutableStateFlow<Set<String>>(emptySet())
    val selectedTypes: StateFlow<Set<String>> = _selectedTypes

    private val _isAndFilter = MutableStateFlow(true)
    val isAndFilter: StateFlow<Boolean> = _isAndFilter

    private val _searchText = mutableStateOf("")

    init {
        getTemtemList()
        getTypes()
    }

    private fun getTemtemList() {
        getTemtemUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    temtemList = result.data ?: emptyList()
                    _state.value =
                        TemtemListState(data = temtemList)
                }

                is Resource.Loading -> _state.value = TemtemListState(isLoading = true)
                is Resource.Error -> _state.value =
                    TemtemListState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }

    fun searchTemtem(query: String) {
        _searchText.value = query
        applySearchAndFilter()
    }

    fun resetSearchAndFilters() {
        viewModelScope.launch {
            _state.value = state.value.copy(data = temtemList)
            _selectedTypes.value = emptySet()
            _searchText.value = ""
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

    fun openDrawer() {
        viewModelScope.launch {
            _openDrawerEvent.emit(Unit)
        }
    }

    fun filterByTypes(types: Set<String>) {
        _selectedTypes.value = types
        applySearchAndFilter()
    }

    fun updateIsAndFilter(isAnd: Boolean){
        _isAndFilter.value = isAnd
        applySearchAndFilter()
    }

    private fun applySearchAndFilter() {
        viewModelScope.launch {
            val filteredList = temtemList.filter { temtem ->
                //Filter by type
                if (_selectedTypes.value.isEmpty()) {
                    true
                } else {
                    if (_isAndFilter.value) {
                        //AND filter
                        _selectedTypes.value.all{ type ->
                            temtem.types.contains(type)
                        }
                    } else {
                        //OR filter
                        _selectedTypes.value.any{ type ->
                            temtem.types.contains(type)
                        }
                    }
                }
            }.filter { temtem ->
                //Filter by search
                _searchText.value.isEmpty() || temtem.name.contains(
                    _searchText.value,
                    ignoreCase = true
                )
            }
            _state.value = state.value.copy(data = filteredList)
        }
    }
}