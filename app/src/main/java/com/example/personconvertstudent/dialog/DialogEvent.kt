package com.example.personconvertstudent.dialog

import com.example.personconvertstudent.data.models.Person

sealed class DialogEvent {
    data class OnStudentClick(val person: Person): DialogEvent()
    data class OnTextChange(val text: String): DialogEvent()
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
}

