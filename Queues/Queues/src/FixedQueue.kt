import java.lang.IndexOutOfBoundsException

class FixedQueue<E> {
    private val elements: Array<Any?>
    private var size = 0

    constructor(capacity:  Int){
        this.elements = arrayOfNulls(capacity)
    }

    fun enqueue(element: E){
        if (size == elements.size)
            throw QueueOverflowException()
        elements[size++] = element
    }

    fun deQueue():E{
        if (size == 0)
            throw QueueUnderflowException()
        val oldVal = elements[0]
        elements[0] = null
        System.arraycopy(elements, 1, elements, 0, --size)
        return oldVal as E
    }

    fun front() = try {
        elements[0] as E
    }catch (e: IndexOutOfBoundsException){
        throw QueueUnderflowException()
    }

    fun rear() = try {
        elements[size - 1] as E
    }catch (e: IndexOutOfBoundsException){
        throw QueueUnderflowException()
    }

    fun isEmpty() = size == 0

    class QueueUnderflowException: RuntimeException()
    class QueueOverflowException: RuntimeException()
}