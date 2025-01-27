package com.example.fitnessapp.ui.screens.level_screen

import android.annotation.SuppressLint
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
import com.example.fitnessapp.ui.screens.level_screen.components.Content
import com.example.fitnessapp.ui.screens.level_screen.models.LevelList
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.components.TopBarWithLogo
import com.example.fitnessapp.ui.theme.FitnessAppTheme



@SuppressLint("MutableCollectionMutableState")
@Composable
fun PhysicalActivityLevel(onPersonLevel : (String) -> Unit) {

    val levelList by remember {
        mutableStateOf(
            mutableListOf(
                LevelList(
                    levelName = "Beginner",
                    levelImage = R.drawable.ex_stretching,
                ),
                LevelList(
                    levelName = "Intermediate",
                    levelImage = R.drawable.ex_running,
                ),
                LevelList(
                    levelName = "Advanced",
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
            TopBarWithLogo()
        },

        content = {
            Content(onPersonLevel,levelList,it)
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
        PhysicalActivityLevel(onPersonLevel ={ _ -> })
    }
}