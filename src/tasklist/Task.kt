package tasklist

import com.squareup.moshi.JsonClass
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@JsonClass(generateAdapter = true)
data class Task(
    var tag: String = "I",
    val action: MutableList<String> = mutableListOf<String>(),
    var deadline: LocalDateTime = LocalDateTime.of(2023, 4, 2, 9, 0),
    var priority: String = ""
) {

    fun checkTag() {
        val taskDate = deadline
        val currentDate = LocalDateTime.now()
//        Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0"))
        val numberOfDays = Duration.between(currentDate,taskDate).toDays().toInt()
        tag = when {
            numberOfDays == 0 -> "T"
            numberOfDays > 0 -> "I"
            else -> {
                "O"
            }
        }
    }

    fun setPriority() {
        val sc = Scanner(System.`in`)
        while (true) {
            println("Input the task priority (C, H, N, L):")
            val input = sc.nextLine().uppercase()
            if (input in arrayOf("H", "C", "N", "L") && input != "") {
                priority = input
                break
            }
        }
    }

    fun setDate() {
        var date: String
        while (true) {
            println("Input the date (yyyy-mm-dd):")
            date = readln()
            if (Tasklist(mutableListOf()).isValidDate(date)) {
                break
            } else println("The input date is invalid")
        }
        val temp = date.split("-").toList()
        deadline = LocalDateTime.of(
            temp[0].toInt(), temp[1].toInt(), temp[2].toInt(), deadline.hour, deadline.minute
        )
    }

    fun setTime() {
        var time: String
        while (true) {
            println("Input the time (hh:mm):")
            time = readln()
            if (Tasklist(mutableListOf()).isValidTime(time)) {
                break
            } else println("The input time is invalid")
        }
        val temp = time.split(":").toList()
        deadline = LocalDateTime.of(deadline.year, deadline.month, deadline.dayOfMonth, temp[0].toInt(), temp[1].toInt())
    }

    fun setTask() {
        println("Input a new task (enter a blank line to end):)")
        action.clear()
        while (true) {
            val input = readln().trim()
            if (input.isEmpty()) {
                break
            }
            action.add(input)

        }
        if (action.size == 0) {
            println("The task is blank")

        }
    }


}