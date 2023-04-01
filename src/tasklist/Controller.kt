package tasklist


import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.util.*

object Controller {
    val moshi = Moshi.Builder()
        .add(JsonDateTimeAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val taskAdapter = moshi.adapter<List<Task>>(
        Types.newParameterizedType(List::class.java, Task::class.java)
    )
    val jsonFile = File("tasklist.json")
    var tasklist=Tasklist(mutableListOf())


    fun init(){
        if(jsonFile.exists()){
            val json= jsonFile.readText()
            tasklist.tasklist = taskAdapter.fromJson(json)!!.toMutableList()
        } else{
            jsonFile.createNewFile()
        }
    }

    fun start() {
        init()

        val sc = Scanner(System.`in`)
        while (true) {
            println("Input an action (add, print, edit, delete, end):")
            when (sc.nextLine().lowercase()) {
                "add" -> tasklist.add()
                "print" -> tasklist.print()
                "edit" -> tasklist.edit()
                "delete" -> tasklist.delete()
                "end" -> {
                    tasklist.end()
                    break
                }

                else -> println("The input action is invalid")
            }
        }


    }

    fun beforeEnd() {
        val saveJson = taskAdapter.toJson(tasklist.tasklist)
        println(saveJson)
        jsonFile.writeText(saveJson)
    }
}