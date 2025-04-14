package com.prueba.appmle.ui.theme

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.animation.core.tween
import com.prueba.appmle.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var isValidEmail by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var isValidPassword by remember { mutableStateOf(false) }

    var passwordVisible by remember { mutableStateOf(false) }

    val letterSpacing = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        letterSpacing.animateTo(
            targetValue = 4f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color4)) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.svgtoplogin),
            contentDescription = "SVG top"
        )
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                ) {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 20.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color7
                )
            ) {
                Column (
                    Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp,20.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "INICIAR SESION",
                        style = Typography.titleLarge.copy(
                            letterSpacing = letterSpacing.value.sp
                        ),
                        color = Color5
                    )
                    RowEmail(
                        email = email,
                        emailChange = {
                            email = it
                            isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        },
                        isValidEmail
                    )
                    RowPassword(
                        password = password,
                        passwordChange = {
                            password = it
                            isValidPassword = password.length >= 6
                        },
                        passwordVisible = passwordVisible,
                        passwordVisibleChange = {
                            passwordVisible = !passwordVisible
                        },
                        isValidPassword
                    )
                    RowButtonLogin(
                        context = context,
                        isValidEmail,
                        isValidPassword
                    )
                }
            }
        }
    }
}

@Composable
fun RowButtonLogin(
    context: Context,
    isValidEmail: Boolean,
    isValidPassword: Boolean
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center

    ) {
        Button(
            modifier = Modifier.width(160.dp).height(50.dp),
            onClick = { login(context) },
            enabled = isValidEmail && isValidPassword,
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color5,
                disabledContainerColor = Color4,
                disabledContentColor = Color6
            )
        ) {
            Text(text = "ingresar".uppercase(),style = Typography.bodyMedium)
        }
    }
}

fun login(context: Context) {
    Toast.makeText(context, "FAKE LOGIN", Toast.LENGTH_LONG).show()
}

@Composable
fun RowEmail(
    email: String,
    emailChange: (String) -> Unit,
    isValid: Boolean
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = emailChange,
            label = { Text(text = "Email", style = Typography.bodyLarge) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            singleLine = true,
            textStyle = Typography.bodyLarge,
            colors = if (isValid) {
                TextFieldDefaults.colors(
                    focusedLabelColor = Color3,
                    unfocusedLabelColor = Color3,
                    focusedTextColor = Color1,
                    unfocusedTextColor = Color1,
                    focusedContainerColor = Color6,
                    unfocusedContainerColor = Color6,
                    cursorColor = Color5,
                    focusedIndicatorColor = Color8,
                    unfocusedIndicatorColor = Color8,
                )
            } else {
                TextFieldDefaults.colors(
                    focusedLabelColor = Color3,
                    unfocusedLabelColor = Color3,
                    focusedTextColor = Color1,
                    unfocusedTextColor = Color1,
                    focusedContainerColor = Color6,
                    unfocusedContainerColor = Color6,
                    focusedIndicatorColor = Color5,
                    unfocusedIndicatorColor = Color5,
                    cursorColor = Color5,
                )
            }
        )
    }
}

@Composable
fun RowPassword(
    password: String,
    passwordChange: (String) -> Unit,
    passwordVisible: Boolean,
    passwordVisibleChange: () -> Unit,
    isValidPassword: Boolean
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = passwordChange,
            label = { Text(text = "Contraseña",style = Typography.bodyLarge,) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            maxLines = 1,
            singleLine = true,
            textStyle = Typography.bodyLarge,
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(
                    onClick = passwordVisibleChange
                ) {
                    Icon(
                        imageVector = image,
                        contentDescription = "ver Contraseña"
                    )
                }
            },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            colors = if (isValidPassword) {
                TextFieldDefaults.colors(
                    focusedLabelColor = Color3,
                    unfocusedLabelColor = Color3,
                    focusedTextColor = Color1,
                    unfocusedTextColor = Color1,
                    focusedContainerColor = Color6,
                    unfocusedContainerColor = Color6,
                    cursorColor = Color5,
                    focusedIndicatorColor = Color8,
                    unfocusedIndicatorColor = Color8,
                )
            } else {
                TextFieldDefaults.colors(
                    focusedLabelColor = Color3,
                    unfocusedLabelColor = Color3,
                    focusedTextColor = Color1,
                    unfocusedTextColor = Color1,
                    focusedContainerColor = Color6,
                    unfocusedContainerColor = Color6,
                    focusedIndicatorColor = Color5,
                    unfocusedIndicatorColor = Color5,
                    cursorColor = Color5,
                    errorIndicatorColor = Color9
                )
            }
        )
    }
}

//@Composable
//fun RowImage() {
//    Row(
//        Modifier
//            .fillMaxWidth()
//            .padding(10.dp),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        Image(
//            modifier = Modifier.width(75.dp),
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "Image login"
//        )
//    }
//}