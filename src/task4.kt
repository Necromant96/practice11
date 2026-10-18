data class Student(
    val name: String,
    val group: String,
    val scores: List<Int>
)

fun getAverageScore(student: Student): Double {
    return student.scores.average()
}

fun classReport(students: List<Student>) {
    println("=== Студенты по среднему баллу ===")
    val sorted = students.sortedByDescending { getAverageScore(it) }
    for (s in sorted) {
        val avg = getAverageScore(s)
        println("${s.name} (${s.group}) — ${"%.2f".format(avg)}")
    }
    println()

    println("=== Лучший в группе ===")
    val byGroup = students.groupBy { it.group }
    val bestInGroup = byGroup.mapValues { (_, list) ->
        list.maxByOrNull { getAverageScore(it) }
    }
    for ((group, student) in bestInGroup) {
        val avg = getAverageScore(student!!)
        println("$group: ${student.name} (${"%.2f".format(avg)})")
    }
    println()

    println("=== Средний балл по группам ===")
    val avgByGroup = byGroup.mapValues { (_, list) ->
        list.map { getAverageScore(it) }.average()
    }
    for ((group, avg) in avgByGroup) {
        println("$group: ${"%.2f".format(avg)}")
    }
    println()

    println("=== Отличники (средний >= 4.0) ===")
    val excellent = students.filter { getAverageScore(it) >= 4.0 }
    for (s in excellent) {
        println("${s.name} — ${"%.2f".format(getAverageScore(s))}")
    }
    println()

    println("=== Должники (есть двойка) ===")
    val debtors = students.filter { student ->
        student.scores.any { it == 2 }
    }
    for (s in debtors) {
        println("${s.name} — оценки: ${s.scores}")
    }
}

fun main() {
    val students = listOf(
        Student("Иванов", "ИС-21", listOf(5, 4, 5, 4, 5)),
        Student("Петрова", "ИС-21", listOf(3, 4, 3, 4, 3)),
        Student("Сидоров", "ПИ-22", listOf(5, 5, 4, 5, 5)),
        Student("Козлова", "ПИ-22", listOf(2, 3, 4, 3, 2)),
        Student("Смирнов", "ИС-21", listOf(4, 4, 4, 5, 4)),
        Student("Васильева", "ПИ-22", listOf(5, 5, 5, 4, 5))
    )

    classReport(students)
}