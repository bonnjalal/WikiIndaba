package com.bonnjalal.wikiindaba.presentation.ui

import android.widget.Toast
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import com.bonnjalal.wikiindaba.common.ext.isValidEmail
import com.bonnjalal.wikiindaba.common.snackbar.SnackbarManager
import com.bonnjalal.wikiindaba.data.repository.MainRepository
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import com.bonnjalal.wikiindaba.presentation.model.Program
import com.bonnjalal.wikiindaba.presentation.service.AccountService
import com.bonnjalal.wikiindaba.presentation.state.DataState
import com.bonnjalal.wikiindaba.presentation.state.LoginUiState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.bonnjalal.wikiindaba.R.string as AppText


@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository,
    private val accountService: AccountService,
    savedStateHandle: SavedStateHandle
): IndabaViewModel() {

    /**
     * Login logic
     */
    var loginUiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = loginUiState.value.email
    private val password
        get() = loginUiState.value.password

    fun onEmailChange(newValue: String) {
        loginUiState.value = loginUiState.value.copy(email = newValue)
    }
    fun onPasswordChange(newValue: String) {
        loginUiState.value = loginUiState.value.copy(password = newValue)
    }

    fun onSignInClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

//        if (email.isBlank()) {
//            SnackbarManager.showMessage(AppText.empty_username_error)
//            return
//        }
        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            SnackbarManager.showMessage(AppText.login_success)
//            openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }



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