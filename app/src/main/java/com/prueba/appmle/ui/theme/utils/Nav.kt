package com.prueba.appmle.ui.theme.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun Nav(
    navController: NavController,
    items: List<NavItem>,
    modifier: Modifier
) {
    NavigationBar(
        modifier = modifier
            .shadow(
                elevation = 12.dp,

            ),
        containerColor = Color7,
        contentColor = Color1,

    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color7,
                    indicatorColor = Color4
                ),
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(
                    item.title,
                    style = Typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    )
                )},
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
