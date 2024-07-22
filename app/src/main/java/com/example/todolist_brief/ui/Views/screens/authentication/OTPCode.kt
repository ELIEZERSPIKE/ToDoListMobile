


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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.views.layout.BackTopAppBar
import com.example.todolist_brief.R
import com.example.todolist_brief.ui.Views.AuthRouteScreens
import com.example.todolist_brief.ui.Views.MainRouteScreens
import com.example.todolist_brief.ui.Views.StartRouteScreens
import com.example.todolist_brief.ui.components.DefaultTextField
import com.example.todolist_brief.ui.theme.ToDoListBriefTheme
import com.example.todolist_brief.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun OTPCode(navController: NavController, email: String = "") {
    val authViewModel: AuthViewModel = viewModel()
    val authState = authViewModel.state
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var code by rememberSaveable { mutableStateOf("") }
    var isCodeError by rememberSaveable { mutableStateOf(false) }
    var isCodeErrorText by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }

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
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_4)),
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                        .padding(horizontal = dimensionResource(id = R.dimen.size_8))
                ) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_4)))
                    Text(
                        text = stringResource(id = R.string.otp_code),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(id = R.string.otp_code_text),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_3))
                    ) {
                        DefaultTextField(
                            value = code,
                            onValueChange = { code = it },
                            isError = isCodeError,
                            errorText = isCodeErrorText,
                            label = stringResource(id = R.string.otp_code),
                            placeholder = stringResource(id = R.string.otp_code_placeholder)
                        )
                    }
                    Button(
                        onClick = {
                            isLoading = true
                            scope.launch {
                                authViewModel.checkOtpCode(email = email, code = code)
                                if (authState.otpCodes.isEmpty()) {
                                    isCodeError = true
                                    isCodeErrorText = context.getString(R.string.otp_code_text)
                                    isLoading = false
                                } else {
                                    //authViewModel.activateUserByEmail(email = email)
                                    navController.navigate(MainRouteScreens.HomeScreen.path) {
                                        popUpTo(StartRouteScreens.StartScreen.path) { inclusive = true }
                                        popUpTo(AuthRouteScreens.RegistrationScreen.path) { inclusive = true }
                                        popUpTo(AuthRouteScreens.OTPCodeScreen.path) { inclusive = true }
                                    }
                                }
                            }
                        },
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_3)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = if (isLoading) stringResource(id = R.string.loading) else stringResource(id = R.string.submit))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OTPCodePreview() {
    ToDoListBriefTheme {
        OTPCode(navController = rememberNavController())
    }
}


