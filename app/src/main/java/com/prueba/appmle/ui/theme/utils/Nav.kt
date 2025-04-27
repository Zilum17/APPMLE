package com.prueba.appmle.ui.theme.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
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
            val animationProgress by animateFloatAsState(
                targetValue = if (currentRoute == item.route) 1f else 0f,
                animationSpec = tween(durationMillis = 200)
            )
            CustomNavBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
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

@Composable
fun RowScope.CustomNavBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    indicatorShape: Shape = RoundedCornerShape(4.dp),
    indicatorColor: Color = Color4,
    selectedIconColor: Color = Color7,
    selectedTextColor: Color = Color4,
    unselectedIconColor: Color = Color3,
    unselectedTextColor: Color = Color3,
    animationDuration: Int = 200
) {
    val animationProgress by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(animationDuration)
    )
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .weight(1f)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            CompositionLocalProvider(
                LocalContentColor provides if (selected) selectedIconColor else unselectedIconColor
            ) {
                Box(
                    modifier = Modifier
                        .clip(indicatorShape)
                        .background(
                            if (selected) indicatorColor.copy(alpha = animationProgress)
                            else Color.Transparent
                        )
                        .padding(16.dp, 6.dp)
                ) {
                    icon()
                }
            }
            if (label != null) {
                Spacer(Modifier.height(4.dp))
                CompositionLocalProvider(
                    LocalContentColor provides if (selected) selectedTextColor else unselectedTextColor
                ) {
                    label()
                }
            }
        }
    }
}
