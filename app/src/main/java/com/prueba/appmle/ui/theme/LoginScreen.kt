package com.prueba.appmle.ui.theme

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.prueba.appmle.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
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

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    val context = LocalContext.current
    val email: String by viewModel.email.observeAsState(initial = "")
    val isValidEmail: Boolean by viewModel.isValidEmail.observeAsState(initial = false)

    val password: String by viewModel.password.observeAsState(initial = "")
    val isValidPassword: Boolean by viewModel.isValidPassword.observeAsState(initial = false)

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    var passwordVisible by remember { mutableStateOf(false) }

    val letterSpacing = remember { Animatable(1f) }
    val interactionSource = remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()

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
                        .height(650.dp),
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
                            modifier = Modifier.padding(25.dp, 40.dp).fillMaxWidth(),
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
                            text = "Inicia Sesion para continuar",
                            style = Typography.bodyLarge,
                            color = Color3
                        )
                        RowEmail(
                            email = email,
                            emailChange = { newEmail ->
                                viewModel.updateEmail(newEmail)
                            },
                            isValid = isValidEmail
                        )
                        RowPassword(
                            password = password,
                            passwordChange = { newPassword ->
                                viewModel.updatePassword(newPassword)
                            },
                            isValidPassword = isValidPassword,
                            passwordVisible = passwordVisible,
                            passwordVisibleChange = {
                                passwordVisible = !passwordVisible
                            }
                        )
                        Text(
                            text = "¿Olvidaste tu contraseña?",
                            modifier = Modifier
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) {
                                    println("¡Clic en el texto!")
                                }
                                .padding(25.dp, 10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.End,
                            color = Color5,
                            style = Typography.bodyLarge
                        )
                        RowButtonLogin(
                            isActive = isValidPassword && isValidEmail,
                        ) {
                            coroutineScope.launch{
                                val loginSuccess = viewModel.login(context)
                                if (loginSuccess) {
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
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
                                text = "¿No tienes una cuenta?",
                                style = Typography.bodyLarge,
                                color = Color3
                            )
                            Text(
                                text = "Registrate",
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        navController.navigate("register") {
                                            popUpTo("login") { inclusive = true }
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
fun RowButtonLogin(
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
@Composable
fun RowEmail(
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