package com.example.input_validation.ui.views

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.input_validation.ui.components.states.EmailState
import com.example.input_validation.ui.components.states.NameState
import com.example.input_validation.ui.components.states.PassState
import com.example.input_validation.ui.model.RegisterUser

class RegisterViewModel : ViewModel() {

    var regUser: RegisterUser = RegisterUser()

    var userName: NameState = NameState()
    var errorMsgName: MutableState<String?> = mutableStateOf(null)

    var userEmail: EmailState = EmailState()
    var errorMsgEmail: MutableState<String?> = mutableStateOf(null)

    var userPass: PassState = PassState()
    var errorMsgPass: MutableState<String?> = mutableStateOf(null)

    var userRePass: PassState = PassState()
    var errorMsgRePass: MutableState<String?> = mutableStateOf(null)

    var isEnabledRegisterButton: MutableState<Boolean> = mutableStateOf(false)

    fun validateUserName() {
        userName.validate()
        errorMsgName.value = userName.error
        shouldEnableRegButton()
    }

    fun validateUserEmail() {
        userEmail.validate()
        errorMsgEmail.value = userEmail.error
        shouldEnableRegButton()
    }

    fun validateUserPass() {
        userPass.validate()
        errorMsgPass.value = userPass.error
        shouldEnableRegButton()
    }

    fun validateUserRePass() {
        if (userPass.text == userRePass.text) {
            userRePass.validate()
            errorMsgRePass.value = userRePass.error
        } else {
            errorMsgRePass.value = "Las contraseñas no coinciden"
        }
        shouldEnableRegButton()
    }

    private fun shouldEnableRegButton() {
        isEnabledRegisterButton.value = errorMsgName.value.isNullOrBlank()
                && userName.text.isNotEmpty()
                && errorMsgEmail.value.isNullOrBlank()
                && userEmail.text.isNotEmpty()
                && errorMsgPass.value.isNullOrBlank()
                && userPass.text.isNotEmpty()
                && errorMsgRePass.value.isNullOrBlank()
                && userRePass.text.isNotEmpty()
    }

    fun register() {
        regUser.name = userName.text
        regUser.email = userEmail.text
        regUser.pass = userPass.text
        regUser.confirmPass = userRePass.text
    }

}