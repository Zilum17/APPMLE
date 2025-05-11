package com.prueba.appmle.ui.theme.utils.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.prueba.appmle.ui.theme.utils.Color3
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Typography

@Composable
fun SearchBar(
    onSearch: (String) -> Unit = {}
) {
    val searchText = remember { mutableStateOf(TextFieldValue("")) }
    Row (
        modifier = Modifier
            .shadow(4.dp)
            .fillMaxWidth()
            .background(Color7)
            .height(75.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        BasicTextField(
            value = searchText.value,
            singleLine = true,
            onValueChange = {
                searchText.value = it
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
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(searchText.value.text)
                }
            ),
        )
        IconButton (
            onClick = {
                onSearch(searchText.value.text)
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
}