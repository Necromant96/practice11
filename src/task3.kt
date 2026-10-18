fun findSynonyms(dictionary: Map<String, List<String>>, word: String): List<String> {
    return dictionary[word] ?: emptyList()
}

fun addSynonym(
    dictionary: MutableMap<String, MutableList<String>>,
    word: String,
    synonym: String
) {
    if (dictionary.containsKey(word)) {
        dictionary[word]!!.add(synonym)
    } else {
        dictionary[word] = mutableListOf(synonym)
    }
}

fun getAllWords(dictionary: Map<String, List<String>>): List<String> {
    val all = dictionary.keys + dictionary.values.flatMap { it }
    return all.distinct()
}

fun main() {
    val dictionary = mutableMapOf(
        "быстрый" to mutableListOf("стремительный", "скорый", "проворный"),
        "красивый" to mutableListOf("прекрасный", "очаровательный", "симпатичный"),
        "умный" to mutableListOf("смышленый", "разумный", "мудрый"),
        "большой" to mutableListOf("огромный", "крупный"),
        "маленький" to mutableListOf("крошечный", "миниатюрный", "небольшой")
    )

    println("Синонимы слова 'быстрый': ${findSynonyms(dictionary, "быстрый")}")
    println("Синонимы слова 'медленный': ${findSynonyms(dictionary, "медленный")}")
    println()

    addSynonym(dictionary, "быстрый", "шустрый")
    addSynonym(dictionary, "тихий", "спокойный")

    println("После добавления:")
    println("Синонимы 'быстрый': ${findSynonyms(dictionary, "быстрый")}")
    println("Синонимы 'тихий': ${findSynonyms(dictionary, "тихий")}")
    println()

    println("Все слова и синонимы:")
    println(getAllWords(dictionary))
}