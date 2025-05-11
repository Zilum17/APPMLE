package com.prueba.appmle.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.prueba.appmle.model.Course
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Typography
import com.prueba.appmle.ui.theme.utils.search.CardSearch
import com.prueba.appmle.ui.theme.utils.search.SearchBar
import com.prueba.appmle.viewmodel.CoursesViewModel
import java.text.Normalizer


@Composable
fun SearchScreen(
    coursesViewModel: CoursesViewModel,
    navController: NavController
) {
    val data: List<Course> by coursesViewModel.courses.observeAsState(initial = emptyList())
    val filteredData = remember { mutableStateOf<List<Course>>(data) }

    fun filterItems(query: String, items: List<Course>): List<Course> {
        return if (query.isBlank()) {
            items
        } else {
            val normalizedQuery = Normalizer.normalize(query, Normalizer.Form.NFD)
                .replace("\\p{M}".toRegex(), "")
                .lowercase()

            items.filter {
                val normalizedTitle = Normalizer.normalize(it.title, Normalizer.Form.NFD)
                    .replace("\\p{M}".toRegex(), "")
                    .lowercase()
                normalizedTitle.contains(normalizedQuery)
            }
        }
    }
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color6)
    ) {
        Column (
            Modifier
                .fillMaxSize()
        ) {
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
            SearchBar(
                onSearch = { query ->
                    filteredData.value = filterItems(query, data)
                }
            )
            Spacer(modifier = Modifier.height(24.dp).fillMaxWidth())
            if (filteredData.value.isEmpty()) {
                Text(
                    text = "No se encontraron resultados",
                    style = Typography.bodyMedium,
                    color = Color4,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            } else {
                LazyColumn {
                    items(filteredData.value) { course ->
                        CardSearch(
                            course,
                            navigate = {
                                navController.navigate(it)
                            }
                        )
                    }
                }
            }
        }
    }
}