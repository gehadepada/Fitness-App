package com.example.fitnessapp.ui.screens.level_screen.components


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.screens.level_screen.models.LevelList
import com.example.fitnessapp.R



@Composable
fun Content(levelList: List<LevelList>, it : PaddingValues) {

    var personLevel = remember { -1 }

    Column(
        modifier = Modifier
            .padding(it),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.physical_activity_level),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 10.dp)
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var oldSelected : MutableState<Boolean> ?= null

            items(levelList.size) { i ->
                val isSelected = remember { mutableStateOf(levelList[i].isSelected) }

                val border = if (isSelected.value)
                    BorderStroke(3.dp, colorScheme.primary)
                 else BorderStroke(3.dp, colorScheme.onBackground)

                RowElements(
                    levelList[i],
                    modifier = Modifier.clickable {
                        if (!isSelected.value) {
                            if (oldSelected?.value != null)
                                oldSelected?.value = false
                            oldSelected = isSelected
                            isSelected.value = true

                            personLevel =  i
                        }
                    },
                    border = border
                )
            }

        }

        Spacer(modifier = Modifier.fillMaxHeight(0.5f))

        CardElement(
            border = BorderStroke(2.dp, colorScheme.primary),
            levelList = (R.string.continue_),
            modifier = Modifier.clickable {

                Log.i("NameLevel","$personLevel")

            }
        )

    }
}

@Composable
fun RowElements(levelList: LevelList, modifier: Modifier = Modifier, border:BorderStroke = BorderStroke(3.dp, MaterialTheme.colorScheme.onBackground)) {
    Row(
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Image(
            modifier = Modifier
                .width(73.dp)
                .height(73.dp)
                .padding(end = 8.dp),
            painter = painterResource(
                id = levelList.levelImage
            ),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        )

        CardElement(levelList.levelName, modifier, border)
    }


}

@Composable
fun CardElement(
    levelList: Int,
    modifier: Modifier = Modifier,
    border: BorderStroke
) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .width(227.dp)
            .height(61.dp),
        border = border,
        shape = CircleShape,


        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = levelList),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}