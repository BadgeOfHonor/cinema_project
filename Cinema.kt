package cinema

fun main() {

    fun show(kinoArr: List<MutableList<String>>) {
        kinoArr.let { println("\nCinema:"); it.forEach { println(it.joinToString(" ")) }}
    }

    fun buy(kinoArr: List<MutableList<String>>, costArr: List<List<Int>>) {
        var row = 0
        var seat = 0
        while (true) {
            println("\nEnter a row number: ")
            row = readln().toInt()
            if (row > kinoArr.lastIndex) { println("Wrong input!"); continue }
            println("Enter a seat number in that row: ")
            seat = readln().toInt()
            if (seat > kinoArr[row].lastIndex) { println("Wrong input!"); continue }
            if (kinoArr[row][seat] == "B") { println("That ticket has already been purchased!"); continue }
            break
        }

        println("Ticket price: \$${costArr[row - 1][seat - 1]}")
        kinoArr[row][seat] = "B"
    }

    fun Stat(kinoArr: List<MutableList<String>>, costArr: List<List<Int>>) {
        val k = kinoArr.sumOf { it.count { i -> i == "B" } }
        println("Number of purchased tickets: $k")
        val o = kinoArr.lastIndex * kinoArr[0].lastIndex
        val p = "%.2f".format(k * 100.0 / o)
        println("Percentage: $p%")
        val c = kinoArr.mapIndexed { i, mls -> mls.mapIndexed { j, s ->  if (s == "B") costArr[i - 1][j - 1] else 0 } }
            .sumOf { it.sum() }
        println("Current income: \$$c")
        val t = costArr.sumOf { it.sum() }
        println("Total income: \$$t")
    }

    fun choose() {
        println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
    }

    println("Enter the number of rows: ")
    val rows = readln().toInt()
    println("Enter the number of seats in each row: ")
    val seats = readln().toInt()
    val kinoArr: List<MutableList<String>> = List(rows + 1) { i ->
        MutableList(seats + 1) { j -> if (i == 0) if (j == 0)" " else j.toString() else if (j == 0) i.toString() else "S" } }
    val costArr = List(rows) { i -> List(seats) { j -> if (rows * seats <= 60) 10 else if (i < rows / 2) 10 else 8 } }

    while (true) {
        choose()
        when (readln()) {
            "1" -> show(kinoArr)
            "2" -> buy(kinoArr, costArr)
            "3" -> Stat(kinoArr, costArr)
            "0" -> break
        }
    }
}

