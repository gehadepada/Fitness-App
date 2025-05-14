package com.example.fitnessapp.presentation.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    errorMessage: String = "",
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp)),
    modifierHide: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.labelSmall,
        )
        DefaultButton(
            onClick = onContinueClick,
            enabled = continueEnabled,
            modifier = Modifier.fillMaxWidth()
        )

        BackButton(
            onclick = onBackClick,
            enabled = backEnabled,
            modifier = modifierHide
                .fillMaxWidth()
        )
    }
}
