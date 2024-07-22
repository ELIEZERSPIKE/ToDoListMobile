package com.example.todolist_brief

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist_brief.data.room.database.Graph
import com.example.todolist_brief.ui.Views.AuthRouteScreens
import com.example.todolist_brief.ui.Views.Navigation
import com.example.todolist_brief.ui.Views.StartRouteScreens
import com.example.todolist_brief.ui.theme.ToDoListBriefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.example.todolist_brief.data.room.database.Graph.provide(this)

        setContent {
            ToDoListBriefTheme {
                Navigation()
                }
            }
        }


}


@Composable
fun  Start(navController: NavController) {
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.size_4))
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier
            .width(dimensionResource(id = R.dimen.start_content_width)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_4))
        ) {
            Text(text = stringResource(id = R.string.welcome_on_todo),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .fillMaxWidth(),
                    textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_2)))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_4))

            ){
                Image(painter = painterResource(id = R.drawable.boost_color),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.start_icon_size))
                )
                Column(modifier = Modifier
                    .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_2))
                ) { 
                    Text(text = stringResource(id = R.string.boost_productivity),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        maxLines = integerResource(id = R.integer.max_line_1)
                    )
                    Text(text = stringResource(id = R.string.boost_productivity),
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        maxLines = integerResource(id = R.integer.max_line_2)
                    )
                    
                }

            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_4))
            ) {
                Image(painter = painterResource(id = R.drawable.check_color),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.start_icon_size))
                )
                Column(modifier = Modifier
                    .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_2))
                ) {
                    Text(text = stringResource(id = R.string.availability),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        maxLines = integerResource(id = R.integer.max_line_1)
                    )
                    Text(text = stringResource(id = R.string.availability_description),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        maxLines = integerResource(id = R.integer.max_line_2)
                        )

                }

            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_4))
            ) {
                Image(painter = painterResource(id = R.drawable.lock_color),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.start_icon_size))
                )
                Column(modifier = Modifier
                    .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_2))
                ) {
                    Text(text = stringResource(id = R.string.secured_space),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        maxLines = integerResource(id = R.integer.max_line_1)
                    )
                    Text(text = stringResource(id = R.string.secured_space_description),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        maxLines = integerResource(id = R.integer.max_line_2)
                    )

                }
            }
        }
        Button(
            onClick = {
                navController.navigate(AuthRouteScreens.RegistrationScreen.path) {
                    popUpTo(StartRouteScreens.StartScreen.path){
                        inclusive = true
                    }
                }
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_3)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
            ) {
            Text(
                text = stringResource(id = R.string.lunch_app),
                fontWeight = FontWeight.Bold
            )

        }

        
    }

}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    ToDoListBriefTheme {
        Start(navController = rememberNavController())
    }
}