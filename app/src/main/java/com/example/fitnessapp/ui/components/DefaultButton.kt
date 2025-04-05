package com.example.fitnessapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview()
@Composable
fun DefaultButton(
    onClick: () -> Unit = {},
    text: String = "Continue",
    enabled: Boolean = true,
    color: ButtonColors = ButtonDefaults.buttonColors(containerColor = colorScheme.surface),
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .width(250.dp)
            .height(60.dp),
        border = BorderStroke(2.dp, colorScheme.primary),
        colors = color,
        onClick = {
            onClick()
        }
              , enabled = enabled,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}
