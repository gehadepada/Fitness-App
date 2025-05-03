import java.time.LocalDate

data class Food(
    val foodName: String,
    val date: LocalDate,
    val totalAmount: Int,
    val calories: Int
)
