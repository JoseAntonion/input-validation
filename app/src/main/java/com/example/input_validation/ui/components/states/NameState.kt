package com.example.input_validation.ui.components.states

class NameState: TextFieldState(
    //validator = ::isEmailValid,
    validator = { email -> isNameValid(email) },
    errorMessage = { email -> nameErrorMessage(email) }

)

private fun isNameValid(name: String): Boolean {
    return name.length >= 3
}

private fun nameErrorMessage(name: String) = "$name no es valido"