package com.example.input_validation.ui.components.states

class PassState : TextFieldState(
    //validator = ::isEmailValid,
    validator = { pass -> isPassValid(pass) },
    errorMessage = { pass -> passErrorMessage(pass) }

)

private fun isPassValid(pass: String): Boolean {
    return pass.length >= 6
}

private fun passErrorMessage(pass: String) = "$pass no es valido"
