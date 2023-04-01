package tasklist

const val TASK_LENGTH=44
class FormatPrint(val Tasklist: Tasklist) {
    val mapColorTag = mapOf(
        Pair("I", "\u001B[102m \u001B[0m"), Pair("T", "\u001B[103m \u001B[0m"), Pair("O", "\u001B[101m \u001B[0m")
    )

    val mapColorPri = mapOf(
        Pair("C", "\u001B[101m \u001B[0m"),
        Pair("H", "\u001B[103m \u001B[0m"),
        Pair("N", "\u001B[102m \u001B[0m"),
        Pair("L", "\u001B[104m \u001B[0m")
    )
    val endLine = "+----+------------+-------+---+---+--------------------------------------------+"
    val header = "| N  |    Date    | Time  | P | D |                   Task                     |"

    fun print() {
        val tasklist = Tasklist.tasklist
        if (tasklist.size == 0) {
            println("No tasks have been input")
            return
        }
        println(endLine)
        println(header)
        println(endLine)

        for (idx in tasklist.indices) {
            val n = idx + 1
            val (date, time) = tasklist[idx].deadline.toString().split("T")
            val p = mapColorPri[tasklist[idx].priority]
            tasklist[idx].checkTag()
            val d = mapColorTag[tasklist[idx].tag]
            for (i in tasklist[idx].action.indices) {
                val lenghtAction = tasklist[idx].action[i].length
                val task = tasklist[idx].action[i]

                if (lenghtAction > TASK_LENGTH) {
                    if (i == 0) {
                        println("| $n  | $date | $time | $p | $d |${task.substring(0, TASK_LENGTH)}|")
                    } else {
                        println("|    |            |       |   |   |${task.substring(0, TASK_LENGTH)}|")
                    }
                    var startIndex = TASK_LENGTH
                    var endIndex = if (TASK_LENGTH*2 >= lenghtAction) lenghtAction else TASK_LENGTH*2
                    while (startIndex <= endIndex && startIndex < lenghtAction) {
                        val addspace = " ".repeat(TASK_LENGTH - if (endIndex % TASK_LENGTH == 0) TASK_LENGTH else endIndex % TASK_LENGTH)
                        println(
                            "|    |            |       |   |   |${
                                task.substring(
                                    startIndex, endIndex
                                )
                            }${addspace}|"
                        )
                        startIndex += TASK_LENGTH
                        endIndex = if (endIndex + TASK_LENGTH >= lenghtAction) lenghtAction else endIndex + TASK_LENGTH

                    }


                } else {
                    val addSpace = " ".repeat(TASK_LENGTH - lenghtAction)
                    if (i == 0) {
                        println("| $n  | $date | $time | $p | $d |$task$addSpace|")
                    } else {
                        println("|    |            |       |   |   |$task$addSpace|")
                    }
                }
            }



            println(endLine)

        }
    }
}