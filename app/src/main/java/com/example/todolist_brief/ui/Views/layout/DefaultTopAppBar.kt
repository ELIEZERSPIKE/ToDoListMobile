package com.example.todolist_brief.ui.Views.layout

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist_brief.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(){
    TopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.headlineLarge

                )

            }
        },
        actions = {
            IconButton(onClick = {},
                colors = IconButtonDefaults.iconButtonColors(colorResource(id = R.color.input_background))
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                )

            }
            IconButton(onClick = {},
                colors = IconButtonDefaults.iconButtonColors(colorResource(id = R.color.input_background)),
                ) {

            }
        },
    )
}
@Preview
@Composable
fun DefaultTopAppBarPreview(){
    DefaultTopAppBar()
}