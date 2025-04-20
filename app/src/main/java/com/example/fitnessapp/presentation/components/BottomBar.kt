import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        Icons.Default.Home,
        Icons.Default.ShoppingBag,
        Icons.Default.Person,
        Icons.Default.AddShoppingCart
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.onSurface,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, icon ->
                IconButton(onClick = { onItemSelected(index) }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (index == selectedIndex) Color(0xFF7CFC00) else Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBarPreviewWrapper() {
    var selectedIndex by remember { mutableStateOf(0) }

    CustomBottomBar(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomBottomBar() {
    BottomBarPreviewWrapper()
}
