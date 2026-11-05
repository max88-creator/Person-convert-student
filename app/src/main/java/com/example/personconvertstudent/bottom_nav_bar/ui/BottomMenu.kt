package com.example.student_worker.bottom_nav_bar.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.personconvertstudent.ui.theme.PineapleColor
import com.example.student_worker.bottom_nav_bar.data.BottomMenuItem

@Composable
fun BottomMenu(
    selectedItemTitle: String,
    onItemClick: (String) -> Unit
) {
    val items = listOf(
        BottomMenuItem.Person,
        BottomMenuItem.Student
    )

    val bottomPadding = WindowInsets
        .systemBars
        .asPaddingValues()
        .calculateBottomPadding()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = Color.White
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .selectable(
                                selected = selectedItemTitle == item.title,
                                indication = LocalIndication.current,
                                enabled = true,
                                role = Role.Button,
                                onClick = {
                                    onItemClick(item.title)
                                },
                                interactionSource = remember { MutableInteractionSource() }
                            )
                            .padding(8.dp),
                        contentAlignment = Alignment.Center,
                    ) {

                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(item.iconId),
                                contentDescription = "icon",
                                modifier = Modifier
                                    .size(31.dp),
                                tint = PineapleColor
                            )
                        }
                    }
                }
            }
        }
    }
}

