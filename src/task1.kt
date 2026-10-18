fun analyzeNumbers(numbers: List<Int>): Map<String, List<Int>> {
    return mapOf(
        "even" to numbers.filter { it % 2 == 0 },
        "odd" to numbers.filter { it % 2 != 0 },
        "divisibleBy3" to numbers.filter { it % 3 == 0 },
        "greaterThan50" to numbers.filter { it > 50 }
    )
}

fun main() {
    val numbers = listOf(12, 45, 67, 23, 89, 34, 56, 78, 11, 90, 5, 66, 31, 48, 99)

    println("Исходный список: $numbers")
    println()

    val result = analyzeNumbers(numbers)

    for ((category, list) in result) {
        println("=== $category ===")
        println("Числа: $list")
        println("Количество: ${list.size}")
        println("Сумма: ${list.sum()}")
        println()
    }
}