import java.lang.IndexOutOfBoundsException

class Stack<E> {
    private val minCapacity = 12
    private var elements: Array<Any?>
    private var size = 0

    constructor(){
        this.elements = arrayOf()
    }

    constructor(initialCapacity: Int){
        this.elements = arrayOfNulls(initialCapacity)
    }

    fun push(element: E){
        if (size == 0){
            val newArray = arrayOfNulls<Any>(size + if (size < minCapacity / 2)
                minCapacity
            else
                size shr 1)
            System.arraycopy(element, 0, newArray, 0, size)
            elements = newArray
        }
        elements[size++] = element
    }

    fun pop(): E{
        if (size == 0)
            throw StackUnderflowException()
        val index = --size
        val obj = elements[index]
        elements[index] = null
        return obj as E
    }

    fun peek() = try {
        elements[size - 1] as E
    }catch (e: IndexOutOfBoundsException){
        throw StackUnderflowException()
    }

    class StackUnderflowException: RuntimeException()
}