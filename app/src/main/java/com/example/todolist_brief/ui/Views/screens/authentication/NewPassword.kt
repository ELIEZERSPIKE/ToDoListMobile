package com.example.todolist_brief.ui.Views.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist_brief.R

import com.example.todolist_brief.ui.components.DefaultTextField
import com.example.todolist_brief.ui.theme.ToDoListBriefTheme

@Composable

fun NewPassword(navController: NavController){
    var email by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(id = R.dimen.size_4)
        ),
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.size_8))
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_4)))
        Text(text = stringResource(id = R.string.new_password),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(text = stringResource(id = R.string.new_password_text),
            color = MaterialTheme.colorScheme.onBackground,

        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_3))
        ) {
            DefaultTextField(
                value = email,
                onValueChange = { email = it},
                label = stringResource(id = R.string.password),
                placeholder = stringResource(id = R.string.password_placeholder)
            )
            DefaultTextField(
                value = email,
                onValueChange = { email = it},
                label = stringResource(id = R.string.confirm_password),
                placeholder = stringResource(id = R.string.confirm_password_placeholder)

            )

        }
        Button(onClick = {
            navController.popBackStack()
        },
            modifier = Modifier
                .fillMaxWidth(),
            ) {
                Text(text = stringResource(id = R.string.submit))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  NewPasswordPreview(){
    ToDoListBriefTheme {
        NewPassword(navController = rememberNavController())
    }
}