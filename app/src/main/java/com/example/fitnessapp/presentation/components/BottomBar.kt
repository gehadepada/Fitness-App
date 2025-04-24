import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        Icons.Default.Home to "Home",
        Icons.Default.FitnessCenter to "Workout",
        Icons.Default.Person to "Profile",
        Icons.Default.Settings to "Shop"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.onSurface,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, (icon, label) ->
                val animatedSize by animateDpAsState(
                    targetValue = if (index == selectedIndex) 30.dp else 24.dp,
                    label = "iconSize"
                )

                val animatedColor by animateColorAsState(
                    targetValue = if (index == selectedIndex) Color(MaterialTheme.colorScheme.primary.toArgb()) else Color.Gray,
                    label = "iconColor"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { onItemSelected(index) }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = animatedColor,
                        modifier = Modifier.size(animatedSize)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        color = animatedColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBarPreviewWrapper() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    CustomBottomBar(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it }
    )
}

@Preview(showBackground = true)
@Composable
fun FitnessAppTheme() {
    BottomBarPreviewWrapper()
}
