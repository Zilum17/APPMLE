package com.prueba.appmle.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.prueba.appmle.ui.theme.utils.Color3
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Nav
import com.prueba.appmle.ui.theme.utils.Typography

@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO or android.content.res.Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = false
)
@Composable
fun SearchScreen(
//    navController: NavController
) {
//    val filteredData = viewModel.filteredData.collectAsState(initial = emptyList())
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
                    .zIndex(1f)
                    .fillMaxWidth()
                    .background(Color7)
            ){
                Text(
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Buscar".uppercase(),
                    style = Typography.titleLarge.copy(
                        letterSpacing = 4.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    color = Color4
                )
            }
            Spacer(modifier = Modifier.height(1.dp).fillMaxWidth())
            Row (
                modifier = Modifier
                    .shadow(4.dp)
                    .fillMaxWidth()
                    .background(Color7)
                    .height(75.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = "",
                    onValueChange = {

                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 24.dp, end = 8.dp)
                        .background(Color6, RoundedCornerShape(4.dp))
                        .height(40.dp),
                    textStyle = Typography.bodyLarge.copy(
                        color = Color3,
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            innerTextField()
                        }
                    }
                )
                IconButton (
                    onClick = {
//                        onSearch(searchText.value.text)
                              },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Buscar",
                        tint = Color3
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                }
//                items(filteredData.value) { item ->
//                    Text(
//                        text = item.title,
//                        style = MaterialTheme.typography.bodyLarge,
//                        modifier = Modifier.padding(vertical = 4.dp)
//                    )
//                }
            }
        }
    }
}