import java.util.LinkedList
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun mainn(args: Array<String>) {
    val list = listOf(10, 2, 19, 99, 22, 23, 18, 32, 12, 20, 92, 29)
    val linkedList = LinkedList<Int>(list)
    linkedList.sort()

    for (i in linkedList)
        println("List item $i")

    println("==============================")
    println("Index of 18 is ${linkedList.binarySearch(18)}")
}

data class MyDataClass(val someNumericalValue: Int, val someStringValue: String)
fun mailn(args: Array<String>){
    val dataClassSet = setOf(
        MyDataClass(1, "1st obj"),
        MyDataClass(2, "2nd obj"),
        MyDataClass(3, "3rd obj"),
        MyDataClass(2, "will be added"),
        MyDataClass(3, "3rd obj")
    )

    println("Printing items of dataClassSet one by one")
    for (item in dataClassSet)
        println(item)


    println()
    /**MutableMap example**/
    val map = mapOf(
        "Key One" to 1.0f,
        "Key Two" to 2.0f,
        "Key Three" to 3.0f,
        "Key Three" to 0.0f,
        "Key four" to 4.0f,
        "Key five" to 5.0f
    )
    println("The value at key four is ${map["Key four"]}")
    println("Contents in map")
    for (i in map)
        println("Key: ${i.key}, value:  ${i.value}")

    val mutableMap = map.toMutableMap()
    println("Replacing value at key five - ${mutableMap.put("Key five", 5.5f)}")

    println("Contents of mutableMap")
    for (entry in mutableMap)
        println("Key: ${entry.key}, Value: ${entry.value}")


}

fun main_(args: Array<String>){
    /**the map function**/
    //val list = listOf<Int>(1, 2, 3, 4, 5,6, 7, 8, 10)
    //println("Modified List -> ${list.map { it + 3 }}")

    /**Filter function**/
    val list = 1.until(50).toList()
    println("Filtered list with even numbers -> ${list.filter { it % 2 == 0 }}")

    val filteredList = list.filter {
        val squareRoot = sqrt(it.toDouble()).roundToInt()
        squareRoot * squareRoot == it
    }

    println("Filtered perfect squares => $filteredList")
}

fun main(args: Array<String>){
    val list = listOf(10, 20, 30)
    println("flatMapList -> ${
       list.flatMap { it.rangeTo(it * 2).toList() } 
    }")
}

