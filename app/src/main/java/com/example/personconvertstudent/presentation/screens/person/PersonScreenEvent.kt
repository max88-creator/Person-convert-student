package com.example.personconvertstudent.presentation.screens.person

sealed class PersonScreenEvent {
    data class Navigate(val route: String): PersonScreenEvent()
}