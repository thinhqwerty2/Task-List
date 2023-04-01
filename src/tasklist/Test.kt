package tasklist

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class Task1(
    var tag: String,
    var action: MutableList<String>,
    var deadline: LocalDateTime,
    var priority: String
)
//
//fun main() {
//    val tasks = listOf(
//        Task(
//            tag = "O",
//            action = mutableListOf("do this", "do that"),
//            deadline = LocalDateTime.of(2023, 4, 1, 12, 0),
//            priority = "high"
//        ),
//        Task(
//            tag = "X1111",
//            action = mutableListOf("do something"),
//            deadline = LocalDateTime.of(2023, 4, 2, 9, 0),
//            priority = "low"
//        )
//    )
//
//    val moshi = Moshi.Builder()
//        .add(JsonDateTimeAdapter())
//        .addLast(KotlinJsonAdapterFactory())
//        .build()
//
//    val adapter = moshi.adapter<List<Task>>(
//        Types.newParameterizedType(List::class.java, Task::class.java)
//    )
//    val jsonFile = File("test.json")
//    val json = adapter.toJson(tasks)
//
//    jsonFile.writeText(json)
//    println(json)
//
//
//
//    val tasks2 = adapter.fromJson(jsonFile.readText())
//    println(tasks2)
//}
