package com.example.personconvertstudent.dialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.personconvertstudent.ui.theme.DarkText

@Composable
fun MainDialog(
    dialogController: DialogController,
    onStudentNavigate: () -> Unit
) {
    if (dialogController.openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            },
            title = null,
            text = {
                Text(
                    dialogController.dialogTitle.value.ifEmpty { "UnKnown" },
                    style = TextStyle(
                        color = DarkText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
                //          Spacer(modifier = Modifier.height(10.dp))
                //      if (dialogController.showEditableText.value) {
                TextField(
                    value = dialogController.editableText.value,
                    onValueChange = { text ->
                        dialogController.onDialogEvent(DialogEvent.OnTextChange(text))
                    },
                    label = {
                        Text(
                            text = "Input specialization"
                        )
                    },
                    colors = TextFieldDefaults.colors(
//                            focusedContainerColor = Color.White,
//                            unfocusedContainerColor = LightYellow,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(9.dp),
                    singleLine = true
                )
                //           }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        dialogController.onDialogEvent(DialogEvent.OnConfirm)
                        onStudentNavigate()
                    }
                ) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogController.onDialogEvent(DialogEvent.OnCancel)
                    }
                ) {
                    Text(text = "Cancel")
                }
            },
            shape = RoundedCornerShape(25.dp),
//            containerColor = LightRed

        )
    }
}


