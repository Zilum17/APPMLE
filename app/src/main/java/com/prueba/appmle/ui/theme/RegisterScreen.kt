package com.prueba.appmle.ui.theme

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import com.prueba.appmle.ui.theme.utils.Color1
import com.prueba.appmle.ui.theme.utils.Color3
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Color8
import com.prueba.appmle.ui.theme.utils.Loading
import com.prueba.appmle.ui.theme.utils.Typography
import com.prueba.appmle.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen (authViewModel: AuthViewModel, navController: NavController) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = false
    }
    BackHandler {
        navController.navigate("login") {
            popUpTo("register") { inclusive = true }
        }
    }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val firstNameR: String by authViewModel.firstNameR.observeAsState(initial = "")
    val isValidFirstNameR: Boolean by authViewModel.isValidFirstNameR.observeAsState(initial = false)

    val lastNameR: String by authViewModel.lastNameR.observeAsState(initial = "")
    val isValidLastNameR: Boolean by authViewModel.isValidLastNameR.observeAsState(initial = false)

    val emailR: String by authViewModel.emailR.observeAsState(initial = "")
    val isValidEmailR: Boolean by authViewModel.isValidEmailR.observeAsState(initial = false)

    val passwordR: String by authViewModel.passwordR.observeAsState(initial = "")
    val isValidPasswordR: Boolean by authViewModel.isValidPasswordR.observeAsState(initial = false)

    val passwordCR: String by authViewModel.passwordCR.observeAsState(initial = "")
    val isValidPasswordCR: Boolean by authViewModel.isValidPasswordCR.observeAsState(initial = false)

    val isLoading: Boolean by authViewModel.isLoading.observeAsState(initial = false)

    var passwordVisible by remember { mutableStateOf(false) }
    var passwordVisibleC by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val letterSpacing = remember { Animatable(1f) }
    if (isLoading) {
        Loading()
    } else {
        LaunchedEffect(Unit) {
            letterSpacing.animateTo(
                targetValue = 6f,
                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = 200
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color4)
        ) {
            Column(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(750.dp),
                    shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 20.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color7
                    )
                ) {
                    Column(
                        Modifier
                            .padding(16.dp, 16.dp, 16.dp, 48.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(25.dp, 20.dp).fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "EmprendiAPP".uppercase(),
                            style = Typography.titleLarge.copy(
                                letterSpacing = letterSpacing.value.sp,
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp
                            ),
                            color = Color5
                        )
                        Text(
                            modifier = Modifier.padding(25.dp, 5.dp).fillMaxWidth(),
                            text = "Bienvenido",
                            style = Typography.titleLarge.copy(
                                letterSpacing = 0.5.sp
                            ),
                            color = Color1
                        )
                        Text(
                            modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp).fillMaxWidth(),
                            text = "Registrate para continuar",
                            style = Typography.bodyLarge,
                            color = Color3
                        )
                        RowFirstNameR(
                            firstName = firstNameR,
                            firstNameChange = { newFirstNameR ->
                                authViewModel.updateFirstNameR(newFirstNameR)
                            },
                            isValid = isValidFirstNameR
                        )
                        RowLastNameR(
                            lastName = lastNameR,
                            lastNameChange = { newLastNameR ->
                                authViewModel.updateLastNameR(newLastNameR)
                            },
                            isValid = isValidLastNameR
                        )
                        RowEmailR(
                            email = emailR,
                            emailChange = { newEmail ->
                                authViewModel.updateEmailR(newEmail)
                            },
                            isValid = isValidEmailR
                        )
                        RowPasswordR(
                            password = passwordR,
                            passwordChange = { newPassword ->
                                authViewModel.updatePasswordR(newPassword)
                            },
                            isValidPassword = isValidPasswordR,
                            passwordVisible = passwordVisible,
                            passwordVisibleChange = {
                                passwordVisible = !passwordVisible
                            }
                        )
                        RowPasswordCR(
                            password = passwordCR,
                            passwordChange = { newPassword ->
                                authViewModel.updatePasswordCR(newPassword)
                            },
                            isValidPassword = isValidPasswordCR,
                            passwordVisible = passwordVisibleC,
                            passwordVisibleChange = {
                                passwordVisibleC = !passwordVisibleC
                            }
                        )
                        RowButtonRegister(
                            isActive = isValidEmailR && isValidPasswordR && isValidFirstNameR && isValidLastNameR && isValidPasswordCR,
                        ) {
                            coroutineScope.launch{
                                val registerSuccess = authViewModel.register(context)
                                if (registerSuccess) {
                                    navController.navigate("home") {
                                        popUpTo("register") { inclusive = true }
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(25.dp, 15.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp),
                                text = "¿Tienes una cuenta?",
                                style = Typography.bodyLarge,
                                color = Color3
                            )
                            Text(
                                text = "Inicia Sesion",
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        navController.navigate("login") {
                                            popUpTo("register") { inclusive = true }
                                        }
                                    },
                                textAlign = TextAlign.End,
                                color = Color5,
                                style = Typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowFirstNameR(
    firstName: String,
    firstNameChange: (String) -> Unit,
    isValid: Boolean
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(25.dp, 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(1f),
            value = firstName,
            onValueChange = firstNameChange,
            label = { Text(text = "Nombre", style = Typography.bodyLarge) },
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
                    unfocusedIndicatorColor = Color8
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
                    cursorColor = Color5
                )
            }
        )
    }
}

@Composable
fun RowLastNameR(
    lastName: String,
    lastNameChange: (String) -> Unit,
    isValid: Boolean
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(25.dp, 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(1f),
            value = lastName,
            onValueChange = lastNameChange,
            label = { Text(text = "Apellido", style = Typography.bodyLarge) },
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
                    unfocusedIndicatorColor = Color8
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
                    cursorColor = Color5
                )
            }
        )
    }
}

@Composable
fun RowEmailR(
    email: String,
    emailChange: (String) -> Unit,
    isValid: Boolean
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(25.dp, 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(1f),
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
                    unfocusedIndicatorColor = Color8
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
                    cursorColor = Color5
                )
            }
        )
    }
}

@Composable
fun RowPasswordR(
    password: String,
    passwordChange: (String) -> Unit,
    passwordVisible: Boolean,
    passwordVisibleChange: () -> Unit,
    isValidPassword: Boolean
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(25.dp, 10.dp, 25.dp, 5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(1f),
            value = password,
            onValueChange = passwordChange,
            label = { Text(text = "Contraseña",style = Typography.bodyLarge) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            maxLines = 1,
            singleLine = true,
            textStyle = Typography.bodyLarge,
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
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
                    unfocusedIndicatorColor = Color8
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
                    cursorColor = Color5
                )
            }
        )
    }

}


@Composable
fun RowPasswordCR(
    password: String,
    passwordChange: (String) -> Unit,
    passwordVisible: Boolean,
    passwordVisibleChange: () -> Unit,
    isValidPassword: Boolean,
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(25.dp, 10.dp, 25.dp, 5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(1f),
            value = password,
            onValueChange = passwordChange,
            label = { Text(text = "Repite Contraseña",style = Typography.bodyLarge) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            maxLines = 1,
            singleLine = true,
            textStyle = Typography.bodyLarge,
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
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
                    unfocusedIndicatorColor = Color8
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
                    cursorColor = Color5
                )
            }
        )
    }
}

@Composable
fun RowButtonRegister(
    isActive: Boolean,
    onLoginSelected: () -> Unit
) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(25.dp, 15.dp),
        horizontalArrangement = Arrangement.Center,

        ) {
        Button(
            modifier = Modifier.fillMaxWidth(1f).height(55.dp),
            onClick = { onLoginSelected() },
            enabled = isActive,
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color5,
                disabledContainerColor = Color4,
                disabledContentColor = Color6
            )
        ) {
            Text(
                text = "ingresar".uppercase(),
                style = Typography.bodyMedium
            )
        }
    }
}