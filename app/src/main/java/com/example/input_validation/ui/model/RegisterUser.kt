package com.example.input_validation.ui.model

data class RegisterUser(
    var name: String = "",
    var email: String = "",
    var pass: String = "",
    var rut: String = "",
    var phone: String = "",
    var confirmPass: String = ""
)