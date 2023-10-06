package com.bonnjalal.wikiindaba.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import com.bonnjalal.wikiindaba.data.repository.MainRepository
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import com.bonnjalal.wikiindaba.presentation.model.Program
import com.bonnjalal.wikiindaba.presentation.state.DataState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _dataStateAttendee : MutableLiveData<DataState<List<Attendee>>> = MutableLiveData()
    val dataStateAttendee : LiveData<DataState<List<Attendee>>>
        get() = _dataStateAttendee

    private val _dataStateOrganizer : MutableLiveData<DataState<List<Organizer>>> = MutableLiveData()
    val dataStateOrganizer : LiveData<DataState<List<Organizer>>>
        get() = _dataStateOrganizer

    private val _dataStateProgram : MutableLiveData<DataState<List<Program>>> = MutableLiveData()
    val dataStateProgram : LiveData<DataState<List<Program>>>
        get() = _dataStateProgram

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetAttendeesEvent -> {
                    mainRepository.getAttendees()
                        .onEach { dataState ->
                            _dataStateAttendee.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.GetOrganizersEvent -> {
                    mainRepository.getOrganizers()
                        .onEach { dataState ->
                            _dataStateOrganizer.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.GetProgramEvent -> {
                    mainRepository.getProgram()
                        .onEach { dataState ->
                            _dataStateProgram.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    // Nothing
                }
            }
        }
    }

}


sealed class MainStateEvent() {
    object GetAttendeesEvent: MainStateEvent()
    object GetOrganizersEvent: MainStateEvent()
    object GetProgramEvent: MainStateEvent()
    object None: MainStateEvent()
}