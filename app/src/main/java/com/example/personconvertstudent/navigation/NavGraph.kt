package com.example.personconvertstudent.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personconvertstudent.presentation.screens.person.PersonScreen
import com.example.personconvertstudent.presentation.screens.student.StudentScreen
import com.example.student_worker.bottom_nav_bar.data.BottomMenuItem
import com.example.student_worker.bottom_nav_bar.ui.BottomMenu

@Composable
fun NavGraph(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    var selectedItemTitle by rememberSaveable {
        mutableStateOf(BottomMenuItem.Person.title)
    }

    Scaffold(
        bottomBar = {
            BottomMenu(
                selectedItemTitle = selectedItemTitle,
                onItemClick = { title ->
                    selectedItemTitle = title
                    val destination = when (title) {
                        BottomMenuItem.Person.title -> NavigationRoutes.PersonList
                        BottomMenuItem.Student.title -> NavigationRoutes.StudentList
                        else -> return@BottomMenu
                    }
                    navController.navigate(destination) {
                        popUpTo(NavigationRoutes.PersonList) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
        navController = navController,
        startDestination = NavigationRoutes.PersonList,
        modifier = Modifier
            .padding(paddingValues)
        ) {
            composable<NavigationRoutes.PersonList> {
                PersonScreen(
                  onStudentNavigateClick = {
                      navController.navigate(NavigationRoutes.StudentList)
                  }
                )
            }
            composable<NavigationRoutes.StudentList> {
                StudentScreen(
                    onPeopleListNavigate = {
                        navController.navigate(NavigationRoutes.PersonList)
                    }
                )
            }
        }
    }
}


