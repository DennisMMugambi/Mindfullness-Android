package com.example.mindfullnessapp.Login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.android.common.Utils.Enums.IsSearching
import com.ekenya.rnd.android.common.Utils.Enums.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
   // private val api: MyApi
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _isFound = MutableLiveData<Boolean?>()
    val isFound: MutableLiveData<Boolean?>
        get() = _isFound

    var _searchStatus = MutableStateFlow(IsSearching.NULL)

    private val loginMessageChannel = Channel<Status>()
    val showMessageEvent = loginMessageChannel.receiveAsFlow()

    //For the three input fields
    private val _pin = MutableStateFlow("")
    private val _church = MutableStateFlow("")

    private  var ispinCorrect = false

    //Setters for the fields
    fun setPin(pin: String) {
        _pin.value = pin
    }

    fun setChurch(church : String) {
        _church.value = church
    }





    private fun loginUser() {

    }

    fun validateFields() {
        ispinCorrect = _pin.value.isNotEmpty() && _pin.value.length == 4

        if (!ispinCorrect) {
            sendMessage(Status.OTPINALID)
        } else {
            loginUser()
        }
    }

    fun lookUpAccount(phoneNumber: String) {


    }

    private fun sendMessage(otp : Status) {
        viewModelScope.launch {
            loginMessageChannel.send(otp)
        }
    }
}
