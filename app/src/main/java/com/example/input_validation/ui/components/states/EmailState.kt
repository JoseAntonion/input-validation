package com.example.input_validation.ui.components.states

class EmailState : TextFieldState(
    //validator = ::isEmailValid,
    validator = { email -> isEmailValid(email) },
    errorMessage = { email -> emailErrorMessage(email) }
)

private fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun emailErrorMessage(email: String) = "$email no es valido"