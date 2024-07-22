package com.example.todolist_brief.ui.Views.screens.authentication

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.todolist_brief.data.room.models.User
import com.example.todolist_brief.ui.Views.AuthRouteScreens

import com.example.todolist_brief.ui.components.DefaultCheckBox
import com.example.todolist_brief.ui.components.DefaultTextField
import com.example.todolist_brief.ui.theme.ToDoListBriefTheme
import com.example.todolist_brief.utils.StringConstants
import com.example.todolist_brief.utils.emailLengthValidation
import com.example.todolist_brief.utils.fullNameLengthValidation
import com.example.todolist_brief.utils.passwordLenthValidation
import com.example.todolist_brief.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun Registration(navController: NavController){

    val authViewModel = viewModel(modelClass = AuthViewModel::class.java)

    val authState = authViewModel.state
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var fullName by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordConfirm by rememberSaveable {
        mutableStateOf("")
    }
    var isFullNameError by rememberSaveable {
        mutableStateOf(false)
    }

    var isFullNameErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var isEmailError by rememberSaveable {
        mutableStateOf(false)
    }
    var isEmailErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var isPasswordError by rememberSaveable {
        mutableStateOf(false)
    }
    var isPasswordErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var isTermsAccepted by rememberSaveable {
        mutableStateOf(false)
    }
    var isLoading by rememberSaveable {
        mutableStateOf(false)
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
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_4)))
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        space = dimensionResource(id = R.dimen.size_8))
                ) {
                    Text(text = stringResource(id = R.string.registration),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = stringResource(id = R.string.registration_text),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,

                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_3))
                    ) {
                        DefaultTextField(
                            value = fullName,
                            onValueChange = { fullName = it},
                            isError = isFullNameError,
                            errorText = isFullNameErrorText,
                            label = stringResource(id = R.string.full_name),
                            placeholder = stringResource(id = R.string.full_name_placeholder)

                        )
                        DefaultTextField(
                            value = email,
                            onValueChange = { email = it},
                            isError = isEmailError,
                            errorText = isEmailErrorText,
                            label = stringResource(id = R.string.email),
                            placeholder = stringResource(id = R.string.email_placeholder)

                        )

                        DefaultTextField(
                            value = password,
                            onValueChange = { password = it},
                            isError = isPasswordError,
                            errorText = isPasswordErrorText,
                            label = stringResource(id = R.string.password),
                            placeholder = stringResource(id = R.string.password_placeholder)

                        )
                        DefaultTextField(
                            value = passwordConfirm,
                            onValueChange = { passwordConfirm = it},
                            label = stringResource(id = R.string.confirm_password),
                            placeholder = stringResource(id = R.string.confirm_password_placeholder)

                        )
                    }
                    DefaultCheckBox(
                        label = stringResource(id = R.string.i_accept_terms),
                        isChecked = isTermsAccepted,
                        onValueChange = {
                            isTermsAccepted = it
                        },

                    )
                    Button(
                        onClick = {
                        var isError = false

                        if (!fullNameLengthValidation(fullName)){
                            isFullNameError = true
                            isFullNameErrorText = context.getString(R.string.full_name_error_text)
                            isError = true
                        } else {
                            isFullNameError = false
                            isFullNameErrorText = ""
                        }
                        if (!emailLengthValidation(email)){
                            isEmailError = true
                            isEmailErrorText = context.getString(R.string.email_error_text)
                            isError = true
                        } else {
                            isEmailError = false
                            isEmailErrorText = ""
                        }
                        if (!passwordLenthValidation(password)){
                            isPasswordError = true
                            isPasswordErrorText = context.getString(R.string.password_error_text)
                            isError = true
                        } else if(password != passwordConfirm) {
                            isPasswordError =true
                            isPasswordErrorText = context.getString(R.string.password_confirm_error_text)
                            isError = true
                        } else {
                            isPasswordError = false
                            isPasswordErrorText= ""

                        }
                        if (!isError){
                            isLoading = true

                            scope.launch {
                                authViewModel.getUserByEmail(email)
                                if (!isTermsAccepted){
                                    isFullNameError = true
                                    isFullNameErrorText = context.getString(R.string.email_already_taken)
                                    isLoading = false
                                } else {
                                    if (authState.usersList.isNotEmpty()){
                                        isEmailError = true
                                        isEmailErrorText = context.getString(R.string.email_already_taken)
                                        isLoading = false
                                    }
                                    else{
                                        authViewModel.sendOTPCode(
                                            com.example.todolist_brief.data.room.models.OTPCode(
                                                email = email,
                                                code = StringConstants.DefaultOTPCode.value
                                            )
                                        )
                                        authViewModel.register(user = User(
                                            fullName = fullName,
                                            email = email,
                                            password = password
                                        ) )
                                        navController.navigate(AuthRouteScreens.OTPCodeScreen.path + "/" + email)
                                        isLoading = false
                                    }
                                }

                            }
                        }
                    },
                        
                        enabled = !isLoading,
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_3)),
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Text(text = if(isLoading) stringResource(id = R.string.loading) else stringResource(
                            id = R.string.registration))

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_1))
                    ) {
                        Text(
                            text = stringResource(id = R.string.already_registred),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = stringResource(id = R.string.signing_in),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable { 
                                    navController.navigate(AuthRouteScreens.SignInScreen.path)
                                }
                        )
                        
                        
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.start_content_width)))


                }

            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationPreview(){
    ToDoListBriefTheme {
        Registration(navController = rememberNavController())
    }
}

