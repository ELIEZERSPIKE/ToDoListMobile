package com.example.todolist.ui.views.screens.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


import com.example.todolist_brief.ui.Views.layout.DefaultTopAppBar
import com.example.todolist_brief.ui.theme.ToDoListBriefTheme

@Composable
fun Home(navController: NavController){
    Scaffold(
        topBar = {
            DefaultTopAppBar()
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview(){
    ToDoListBriefTheme{
        Home(navController = rememberNavController())
        }
}
