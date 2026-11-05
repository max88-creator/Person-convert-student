package com.example.personconvertstudent.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationRoutes {
    @Serializable
    data object PersonList : NavigationRoutes {

    }

    @Serializable
    data object StudentList : NavigationRoutes
}



