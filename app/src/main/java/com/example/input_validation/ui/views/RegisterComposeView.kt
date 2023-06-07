package com.example.input_validation.ui.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.input_validation.ui.theme.Purple200

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterComposeView(rvm: RegisterViewModel = viewModel()) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
        Column {
            Text(text = "Registro de usuario")
            ShowUserName(rvm)
            ShowUserEmail(rvm)
            ShowUserPass(rvm)
            ShowUserRePass(rvm)

            ShowRegisterButton(rvm, context)
        }
    }

}

@Composable
fun ShowUserName(rvm: RegisterViewModel) {
    Column {
        TextField(
            value = rvm.userName.text,
            onValueChange = {
                rvm.userName.text = it
                rvm.validateUserName()
            },
            isError = rvm.errorMsgName.value != null,
            maxLines = 1,
            label = {
                Text(text = "Nombre")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text
            )
        )

        rvm.errorMsgName.value?.let {
            ErrorField(it)
        }
    }
}

@Composable
fun ShowUserPass(rvm: RegisterViewModel) {

    val showPass = remember {
        mutableStateOf(false)
    }

    Column {
        TextField(
            value = rvm.userPass.text,
            onValueChange = {
                rvm.userPass.text = it
                rvm.validateUserEmail()
            },
            isError = rvm.errorMsgPass.value != null,
            maxLines = 1,
            label = {
                Text(text = "Password")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text
            ),
            visualTransformation = if (showPass.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showPass.value) {
                    Pair(
                        Icons.Rounded.Lock,
                        Purple200
                    )
                } else {
                    Pair(
                        Icons.Rounded.Lock,
                        Color.Green
                    )
                }

                IconButton(
                    onClick = {
                        showPass.value = !showPass.value
                    }
                )
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor
                    )
                }

            }
        )

        rvm.errorMsgEmail.value?.let {
            ErrorField(it)
        }
    }
}

@Composable
fun ShowUserRePass(rvm: RegisterViewModel) {

    val showPass = remember {
        mutableStateOf(false)
    }

    Column {
        TextField(
            value = rvm.userPass.text,
            onValueChange = {
                rvm.userPass.text = it
                rvm.validateUserEmail()
            },
            isError = rvm.errorMsgPass.value != null,
            maxLines = 1,
            label = {
                Text(text = "Password")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text
            ),
            visualTransformation = if (showPass.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showPass.value) {
                    Pair(
                        Icons.Rounded.Lock,
                        Purple200
                    )
                } else {
                    Pair(
                        Icons.Rounded.Lock,
                        Color.Green
                    )
                }

                IconButton(
                    onClick = {
                        showPass.value = !showPass.value
                    }
                ) {
                    Icon(
                        icon,
                        contentDescription = "bla",
                        tint = iconColor
                    )
                }

            }
        )

        rvm.errorMsgEmail.value?.let {
            ErrorField(it)
        }
    }
}

@Composable
fun ShowUserEmail(rvm: RegisterViewModel) {
    Column {
        TextField(
            value = rvm.userEmail.text,
            onValueChange = {
                rvm.userEmail.text = it
                rvm.validateUserEmail()
            },
            isError = rvm.errorMsgEmail.value != null,
            maxLines = 1,
            label = {
                Text(text = "Email")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text
            )
        )

        rvm.errorMsgEmail.value?.let {
            ErrorField(it)
        }
    }
}

@Composable
fun ShowRegisterButton(rvm: RegisterViewModel, context: Context) {
    Button(
        onClick = {
            rvm.register()
            Toast.makeText(
                context,
                "${rvm.userName} : ${rvm.userEmail} : ${rvm.userPass}",
                Toast.LENGTH_SHORT
            ).show()
        },
        enabled = rvm.isEnabledRegisterButton.value
    ) {
        Text(text = "Registrar")
    }
}

@Composable
fun ErrorField(message: String) {
    Text(
        text = message
    )
}