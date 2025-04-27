package com.prueba.appmle.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Nav
import com.prueba.appmle.ui.theme.utils.Typography
import com.prueba.appmle.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(authViewModel: AuthViewModel, navController: NavController) {
    val context = LocalContext.current
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color6)
    ) {
        Column (
            Modifier
                .fillMaxSize()
        ){
            Row (
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp
                    )
                    .fillMaxWidth()
                    .background(Color7)
            ){
                Text(
                    modifier = Modifier
                        .padding(0.dp, 56.dp, 0.dp, 20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Perfil".uppercase(),
                    style = Typography.titleLarge.copy(
                        letterSpacing = 4.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    color = Color4
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(1f).height(55.dp),
                onClick = {
                    authViewModel.logout(context)
                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }},
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color5,
                    disabledContainerColor = Color4,
                    disabledContentColor = Color6
                )
            ) {
                Text(
                    text = "SALIR".uppercase(),
                    style = Typography.bodyMedium
                )
            }
        }
    }
}