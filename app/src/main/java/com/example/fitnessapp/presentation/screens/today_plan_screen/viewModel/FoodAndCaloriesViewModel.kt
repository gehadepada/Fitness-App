import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class FoodViewModel : ViewModel() {
    private val _foodList = mutableStateListOf<Food>(
        Food("Apple", LocalDate.of(2025, 5, 1), 2, 190),
        Food("Banana", LocalDate.of(2025, 5, 2), 1, 105),
        Food("Chicken", LocalDate.of(2025, 5, 2), 1, 250),
        Food("Rice", LocalDate.of(2025, 5, 3), 1, 200)
    )
    val foodList: List<Food> get() = _foodList

    var selectedDate by mutableStateOf(LocalDate.now())
        private set

    val filteredFoods: List<Food>
        get() = _foodList.filter { it.date == selectedDate }

    fun setDate(date: LocalDate) {
        selectedDate = date
    }
}
