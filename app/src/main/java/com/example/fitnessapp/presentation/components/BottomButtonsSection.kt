package com.example.fitnessapp.presentation.components


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomButtonsSection(
    onContinueClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    continueEnabled: Boolean = true,
    backEnabled: Boolean = true,
    continueMessage: String = "",
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(PaddingValues(start = 24.dp, end = 24.dp, bottom = 48.dp))
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DefaultButton(
            onClick = onContinueClick,
            enabled = continueEnabled,
            message = continueMessage,
            modifier = Modifier.fillMaxWidth()
        )

        BackButton(
            onclick = onBackClick,
            enabled = backEnabled,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
