
package com.example.todolist_brief.ui.Views.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.views.layout.BackTopAppBar
import com.example.todolist_brief.R

import com.example.todolist_brief.ui.components.DefaultTextField
import com.example.todolist_brief.ui.theme.ToDoListBriefTheme

@Composable
fun ForgottenPassword(navController: NavController) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    ToDoListBriefTheme {
        Scaffold(
            topBar = {
                BackTopAppBar(navController = navController)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        space = dimensionResource(id = R.dimen.size_4)
                    ),
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                        .padding(horizontal = dimensionResource(id = R.dimen.size_8))
                ) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_4)))
                    Text(
                        text = stringResource(id = R.string.forgotten_password),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(id = R.string.forgotten_password),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_3))
                    ) {
                        DefaultTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = stringResource(id = R.string.email),
                            placeholder = stringResource(id = R.string.email_placeholder)
                        )
                    }
                    Button(
                        onClick = {
                            // Handle submit action
                        },
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_3)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.submit))
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_1))
                    ) {
                        Text(
                            text = stringResource(id = R.string.not_yet_registred),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = stringResource(id = R.string.register),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForgottenPasswordPreview() {
    ToDoListBriefTheme {
        ForgottenPassword(navController = rememberNavController())
    }
}


