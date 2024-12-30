package com.example.fitnessapp.physicalActivityLevelPage.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.Pieces.Content
import com.example.fitnessapp.Pieces.LevelList
import com.example.fitnessapp.Pieces.TopBar
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.FitnessAppTheme



@Composable
fun PhysicalActivityLevel() {

    val levelList by remember {
        mutableStateOf(
            listOf(
                LevelList(
                    levelName = R.string.beginner,
                    levelImage = R.drawable.ex_stretching,
                ),
                LevelList(
                    levelName = R.string.intermediate,
                    levelImage = R.drawable.ex_running,
                ),
                LevelList(
                    levelName = R.string.advanced,
                    levelImage = R.drawable.ex_exercise,
                )
            )
        )
    }


    Scaffold(
        modifier = Modifier
            .background(colorScheme.background)
            .fillMaxSize(),
        topBar = {
            TopBar()
        },

        content = {
            Content(levelList,it)
        }

    )

}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun Prev() {
    FitnessAppTheme {
        PhysicalActivityLevel()
    }
}