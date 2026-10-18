data class WordStats(
    val word: String,
    val count: Int,
    val length: Int
)

fun analyzeText(text: String): Map<String, Any> {
    val cleaned = text
        .lowercase()
        .replace(Regex("[^a-zа-яё\\s]"), " ")

    val words = cleaned
        .split(Regex("\\s+"))
        .filter { it.length >= 3 }

    val totalWords = words.size
    val uniqueWords = words.distinct().size
    val averageWordLength = if (words.isNotEmpty()) {
        words.map { it.length }.average()
    } else {
        0.0
    }
    val longestWord = words.maxByOrNull { it.length } ?: ""

    val wordFrequency = words
        .groupBy { it }
        .mapValues { it.value.size }

    val top5Words = wordFrequency
        .entries
        .sortedByDescending { it.value }
        .take(5)
        .map { (word, count) -> WordStats(word, count, word.length) }

    return mapOf(
        "totalWords" to totalWords,
        "uniqueWords" to uniqueWords,
        "averageWordLength" to averageWordLength,
        "longestWord" to longestWord,
        "wordFrequency" to wordFrequency,
        "top5Words" to top5Words
    )
}

fun findPhrases(text: String, phraseLength: Int): List<String> {
    val cleaned = text
        .lowercase()
        .replace(Regex("[^a-zа-яё\\s]"), " ")

    val words = cleaned
        .split(Regex("\\s+"))
        .filter { it.isNotEmpty() }

    if (words.size < phraseLength) return emptyList()

    val phrases = mutableListOf<String>()
    for (i in 0..words.size - phraseLength) {
        val phrase = words.subList(i, i + phraseLength).joinToString(" ")
        phrases.add(phrase)
    }

    return phrases
        .groupBy { it }
        .mapValues { it.value.size }
        .entries
        .sortedByDescending { it.value }
        .take(3)
        .map { it.key }
}

fun main() {
    val text = """
        Кот съел рыбу. Потом кот съел ещё одну рыбу.
        Рыба была очень вкусная. Кот остался доволен рыбой.
    """.trimIndent()

    println("Исходный текст:")
    println(text)
    println()

    val stats = analyzeText(text)

    println("=== Статистика текста ===")
    println("Всего слов (от 3 символов): ${stats["totalWords"]}")
    println("Уникальных слов: ${stats["uniqueWords"]}")
    println("Средняя длина слова: ${"%.2f".format(stats["averageWordLength"] as Double)}")
    println("Самое длинное слово: ${stats["longestWord"]}")
    println()

    println("Частота слов:")
    val frequency = stats["wordFrequency"] as Map<String, Int>
    for ((word, count) in frequency.entries.sortedByDescending { it.value }) {
        println("  $word — $count")
    }
    println()

    println("Топ-5 самых частых слов:")
    val top5 = stats["top5Words"] as List<WordStats>
    for (ws in top5) {
        println("  ${ws.word} (длина ${ws.length}) — ${ws.count} раз")
    }
    println()

    println("=== Топ-3 биграммы (фразы длины 2) ===")
    val bigrams = findPhrases(text, 2)
    for (phrase in bigrams) {
        println("  $phrase")
    }
}