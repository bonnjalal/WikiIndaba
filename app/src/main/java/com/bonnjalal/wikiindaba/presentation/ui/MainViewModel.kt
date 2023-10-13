package com.bonnjalal.wikiindaba.presentation.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import com.bonnjalal.wikiindaba.common.LOGIN_SCREEN
import com.bonnjalal.wikiindaba.common.PROGRAM_SCREEN
import com.bonnjalal.wikiindaba.common.ext.isValidEmail
import com.bonnjalal.wikiindaba.common.snackbar.SnackbarManager
import com.bonnjalal.wikiindaba.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.bonnjalal.wikiindaba.data.repository.MainRepository
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import com.bonnjalal.wikiindaba.presentation.model.Program
import com.bonnjalal.wikiindaba.data.online.service.AccountService
import com.bonnjalal.wikiindaba.presentation.state.DataState
import com.bonnjalal.wikiindaba.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject
import com.bonnjalal.wikiindaba.R.string as AppText


@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository,
    private val accountService: AccountService,
    savedStateHandle: SavedStateHandle
): ViewModel() {


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

    fun onSignInClick(navigateAndPopup: (String, String) -> Unit) {
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
            navigateAndPopup(PROGRAM_SCREEN, LOGIN_SCREEN)
        }

    }

    val userState = accountService.currentUser.map { it.isAnonymous}
    fun onSignInAnonymously(navigateAndPopup: (String, String) -> Unit){
        launchCatching {
            accountService.createAnonymousAccount()
            SnackbarManager.showMessage(AppText.anonymous_login_success)
            navigateAndPopup(PROGRAM_SCREEN, LOGIN_SCREEN)
            setStateEvent(MainStateEvent.GetProgramEvent)
        }

    }

    fun onSignOut (clearAndPopUp: (String) -> Unit){
        launchCatching {
            accountService.signOut()
            clearAndPopUp(LOGIN_SCREEN)
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




    /**
     * Program logic
     */

    var searchProgramState = mutableStateOf("")
        private set
    fun onProgramSearchChange(newValue: String) {
        searchProgramState.value = newValue
    }




    private val _dataStateAttendee : MutableLiveData<DataState<List<Attendee>>> = MutableLiveData()
    val dataStateAttendee : LiveData<DataState<List<Attendee>>>
        get() = _dataStateAttendee

    private val _dataStateOrganizer : MutableLiveData<DataState<List<Organizer>>> = MutableLiveData()
    val dataStateOrganizer : LiveData<DataState<List<Organizer>>>
        get() = _dataStateOrganizer

//    private val _dataStateProgram : MutableLiveData<DataState<List<Program>>> = MutableLiveData()
//    val dataStateProgram : LiveData<DataState<List<Program>>>
//        get() = _dataStateProgram

    private var _dataStateProgram = MutableStateFlow<DataState<List<Program>>>(DataState.Loading)
    val dataStateProgram = _dataStateProgram.asStateFlow()



//        get() = _dataStateProgram



    var showPrograms = mutableStateOf(false)
        private set
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
                            /*when (dataState) {

                                is DataState.Success -> {
                                    _dataStateProgram.value = dataState
                                    showPrograms.value = true
                                    Log.e("indaba ViewModel", "dataState success: $dataState")

                                    Log.e("indaba ViewModel", "showPrograms: ${showPrograms.value}")
//                                    Log.e("indaba VeiwModel", " datastate: ${dataStateProgram}")
                                }

                                is DataState.Error -> {
                                    showPrograms.value = false
                                }
                                is DataState.Loading -> {
                                    showPrograms.value = false
                                }
                            }*/
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    // Nothing
                }
            }
        }
    }

    fun getDateTime(startTime: Date, endTime:Date): String {
        val maLocal = Locale.forLanguageTag("ar-MA")
        return try {
            Log.e("ViewModel Time", "time $startTime")
            val dateF = SimpleDateFormat("MMM-dd", Locale.ENGLISH)
            val timeF = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            timeF.timeZone = TimeZone.getTimeZone("GMT+01")
    //            val startTime = Date(startTimestamp * 1000)
    //            val endTime = Date(endTimestamp * 1000)

            dateF.format(startTime)+ ", " + timeF.format(startTime) + " - " + timeF.format(endTime)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
//                logService.logNonFatalCrash(throwable)
            },
            block = block
        )


}


sealed class MainStateEvent() {
    object GetAttendeesEvent: MainStateEvent()
    object GetOrganizersEvent: MainStateEvent()
    data object GetProgramEvent: MainStateEvent()
    object None: MainStateEvent()
}