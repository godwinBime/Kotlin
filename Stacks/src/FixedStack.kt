import java.lang.IndexOutOfBoundsException

class FixedStack<E> {
    private val elements: Array<Any?>
    private var size = 0

    constructor(capacity: Int){
        this.elements = arrayOfNulls(capacity)
    }

    fun push(element: E){
        if (size == elements.size){
            throw StackOverflowException()
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

    fun isEmpty() = size == 0
    fun isFull() = size == elements.size

    class StackOverflowException: RuntimeException()
    class StackUnderflowException: RuntimeException()
}