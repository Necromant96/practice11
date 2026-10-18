data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val rating: Double
)

fun shopReport(products: List<Product>) {
    println("=== Товары по убыванию цены ===")
    val sortedByPrice = products.sortedByDescending { it.price }
    for (p in sortedByPrice) {
        println("${p.name} — ${p.price} руб. (рейтинг ${p.rating})")
    }
    println()

    println("=== Товары с рейтингом >= 4.5 ===")
    val highRated = products.filter { it.rating >= 4.5 }
    for (p in highRated) {
        println("${p.name} — рейтинг ${p.rating}")
    }
    println()

    println("=== Группировка по категориям ===")
    val byCategory = products.groupBy { it.category }
    for ((category, items) in byCategory) {
        println("$category: ${items.map { it.name }}")
    }
    println()

    println("=== Средняя цена по категориям ===")
    val avgPrice = byCategory.mapValues { (_, items) ->
        items.map { it.price }.average()
    }
    for ((category, avg) in avgPrice) {
        println("$category: ${"%.2f".format(avg)} руб.")
    }
    println()

    println("=== Самый дорогой товар в категории ===")
    val mostExpensive = byCategory.mapValues { (_, items) ->
        items.maxByOrNull { it.price }
    }
    for ((category, product) in mostExpensive) {
        println("$category: ${product?.name} (${product?.price} руб.)")
    }
    println()

    println("=== Итого ===")
    println("Всего товаров: ${products.size}")
    println("Общая стоимость: ${products.sumOf { it.price }} руб.")
}

fun main() {
    val products = listOf(
        Product(1, "Смартфон", 25000.0, "electronics", 4.7),
        Product(2, "Наушники", 3500.0, "electronics", 4.3),
        Product(3, "Ноутбук", 55000.0, "electronics", 4.8),
        Product(4, "Хлеб", 45.0, "food", 4.1),
        Product(5, "Молоко", 80.0, "food", 4.6),
        Product(6, "Сыр", 350.0, "food", 4.9),
        Product(7, "Футболка", 1200.0, "clothes", 4.2),
        Product(8, "Джинсы", 2800.0, "clothes", 4.5)
    )

    shopReport(products)
}