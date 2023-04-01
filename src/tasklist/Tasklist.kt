package tasklist

import java.time.LocalDateTime
import java.util.*

class Tasklist(var tasklist: MutableList<Task>) {

    fun print() {
        FormatPrint(this ).print()

    }
    fun add() {
        val task = Task()
        val deadline = inputDeadline()
        val date = deadline[1].split("-").toList()
        val time = deadline[2].split(":").toList()
        task.priority = deadline[0]
        task.deadline = LocalDateTime.of(
            date[0].toInt(), date[1].toInt(), date[2].toInt(), time[0].toInt(), time[1].toInt()
        )
        println("Input a new task (enter a blank line to end):)")
        while (true) {
            val input = readln().trim()
            if (input.isEmpty()) {
                break
            }
            task.action.add(input)

        }
        if (task.action.size == 0) {
            println("The task is blank")

        } else {
            tasklist.add(task)
        }
    }


    fun end() {
        println("Tasklist exiting!")
    }

    private fun inputDeadline(): List<String> {
        var priority = ""
        var date: String
        var time: String
        while (true) {
            println("Input the task priority (C, H, N, L):")
            val input = readln().uppercase()
            if (input in arrayOf("H", "C", "N", "L") && input != "") {
                priority = input
                break
            }
        }
        while (true) {
            println("Input the date (yyyy-mm-dd):")
            date = readln()
            if (isValidDate(date)) {
                break
            } else println("The input date is invalid")
        }
        while (true) {
            println("Input the time (hh:mm):")
            time = readln()
            if (isValidTime(time)) {
                break
            } else println("The input time is invalid")
        }
        return listOf(priority, date, time)

    }

    fun isValidDate(input: String): Boolean {
        try {
            if (!Regex("^\\d{4}-\\d{1,2}-\\d{1,2}$").matches(input)) {
                return false
            }
            val date = input.split("-")
            when (date[1].toInt()) {
                1, 3, 5, 7, 8, 10, 12 -> return date[2].toInt() in 1..31
                4, 6, 9, 11 -> return date[2].toInt() in 1..30
                2 -> when {
                    date[2].toInt() in 1..28 -> return true
                    date[2].toInt() !in 1..29 -> return false
                    date[2].toInt() == 29 -> {
                        return when {
                            date[0].toInt() % 4 == 0 && date[0].toInt() % 100 != 0 -> true
                            date[0].toInt() % 100 == 0 && date[0].toInt() % 400 == 0 -> true
                            else -> false
                        }
                    }
                }

                else -> return false
            }


        } catch (_: Exception) {
            return false
        }

        return true
    }

    fun isValidTime(input: String): Boolean {
        return try {
            if (!Regex("^\\d{1,2}:\\d{1,2}$").matches(input)) {
                return false
            }
            val time = input.split(":")
            time[0].toInt() in 0..23 && time[1].toInt() in 0..59
        } catch (_: Exception) {
            false
        }
    }

    fun delete() {
        this.print()
        if (tasklist.size != 0) {
            while (true) {
                println("Input the task number (1-${tasklist.size}):")
                try {
                    val input = readln().toInt()
                    tasklist.removeAt(input - 1)
                    println("The task is deleted")
                    break
                } catch (e: Exception) {
                    println("Invalid task number")
                }
            }
        }
    }

    fun edit() {
        this.print()
        if (tasklist.size != 0) {
            loop@ while (true) {
                println("Input the task number (1-${tasklist.size}):")
                try {
                    val input:Int = readln().toInt()
                    if(input !in 1..tasklist.size) throw Exception()
                    while (true) {
                        println("Input a field to edit (priority, date, time, task):")
                        when (readln()) {
                            "priority" -> tasklist[input - 1].setPriority()
                            "date" -> tasklist[input - 1].setDate()
                            "time" -> tasklist[input - 1].setTime()
                            "task" -> tasklist[input - 1].setTask()
                            else ->{
                                println("Invalid field")
                                continue
                            }

                        }
                        break@loop
                    }

                } catch (e: Exception) {
                    println("Invalid task number")
                }
            }
            println("The task is changed")
        }
    }

}