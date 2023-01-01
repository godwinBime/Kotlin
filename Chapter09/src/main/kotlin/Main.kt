import java.util.Random

val immutableRandomValue: String by lazy {
    getRandomValue()
}

fun getRandomValue(): String{
    val rand = Random().nextInt()
    return "Value $rand"
}

fun mainp(args: Array<String>){
    println("getRandomValue() will return different values at each call")
    for (i in 0..7){
        println("$i ${getRandomValue()}")
    }

    println()
    println("==================================")
    for (j in 0..4)
        println("$j ${immutableRandomValue}")
}


/**Pure function example**/
class Calculator{
    var anyVariable: Int = 0

    fun add(a: Int, b: Int):Int{//pure function
        return (a + b)
    }

    fun multiply(a: Int, b: Int):Int{ //pure function
        return a * b
    }

    fun subtract(a: Int, b:Int):Int{//pure function
        return a - b
    }

    fun divide(a: Int, b: Int): Int{//pure function
        return a / b
    }

    fun anyFunction(x: Int): Int{//not a pure function
        anyVariable = x + 2
        return anyVariable
    }
}

/**Lambda example**/
fun maint(args: Array<String>){
    var myFunc: (Int) -> Int
    myFunc = {it * 2}
    println("10 * 2 = ${myFunc(10)}")
    myFunc = {it / 2}
    println("10 / 2 = ${myFunc(10)}")
}


/**Higher-order function**/
fun higherOrder(anotherFunc: () -> Unit){
    println("Before anotherFunc()")
    anotherFunc()
    println("After anotherFunc()")
}

fun mainm(args: Array<String>){
    higherOrder {
        println("anotherFunc()..")
    }
}

/**Options type constructor**/
