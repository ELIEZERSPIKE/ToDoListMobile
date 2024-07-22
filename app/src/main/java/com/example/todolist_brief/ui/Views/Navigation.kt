package com.example.todolist_brief.ui.Views

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.ui.views.screens.user.Home
import com.example.todolist_brief.Start
import com.example.todolist_brief.data.room.models.OTPCode
import com.example.todolist_brief.ui.Views.screens.authentication.ForgottenPassword
import com.example.todolist_brief.ui.Views.screens.authentication.NewPassword
import com.example.todolist_brief.ui.Views.screens.authentication.OTPCode
import com.example.todolist_brief.ui.Views.screens.authentication.Registration
import com.example.todolist_brief.ui.Views.screens.authentication.SignIn

@Composable
fun Navigation(
    startDestination: String = StartRouteScreens.StartScreen.path
){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = startDestination
    ) {
        composable(route = StartRouteScreens.StartScreen.path){
            Start(navController = navController)
        }
        composable(route = AuthRouteScreens.RegistrationScreen.path){
            Registration(navController = navController)
        }
        composable(route = AuthRouteScreens.SignInScreen.path){
            SignIn(navController = navController)
        }
        composable(route = AuthRouteScreens.ForgottenPasswordScreen.path){
            ForgottenPassword(navController = navController)
        }

        composable(route = AuthRouteScreens.OTPCodeScreen.path + "/{email}", arguments = listOf(
            navArgument(name = "email"){
                type = NavType.StringType
            }
        )){
            OTPCode(navController = navController, email = it.arguments?.getString("email")!!)
        }

        composable(route = AuthRouteScreens.OTPCodeScreen.path){
            NewPassword(navController = navController)
        }
        composable(route = MainRouteScreens.HomeScreen.path){
            Home(navController = navController)
        }
    }


}