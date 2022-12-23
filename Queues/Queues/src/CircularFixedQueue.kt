import java.lang.IndexOutOfBoundsException

class CircularFixedQueue<E> {
    private val capacity: Int
    private var front = -1
    private var rear = -1
    private val elements: Array<Any?>
    private val size = 0

    constructor(capacity: Int){
        this.capacity = capacity
        this.elements = arrayOfNulls(capacity)
    }

    fun enqueue(element: E){
        if (isFull())
            throw FixedQueue.QueueUnderflowException()
        rear = (rear + 1) % capacity
        elements[rear] = element
        if (front == -1)
            front = rear
    }

    fun dequeue(): E{
        if (isEmpty())
            throw FixedQueue.QueueUnderflowException()
        val oldVal = elements[front]
        elements[front] = null
        if (front == rear){
            front = -1
            rear = -1
        }else
            front = (front + 1) % capacity
        return oldVal as E
    }

    fun front() = try {
        elements[front] as E
    }catch (e: IndexOutOfBoundsException){
        throw FixedQueue.QueueUnderflowException()
    }

    fun rear() = try {
        elements[rear] as E
    }catch (e: IndexOutOfBoundsException){
        throw FixedQueue.QueueUnderflowException()
    }

    private fun isFull() = (rear + 1) % capacity == front
    private fun isEmpty() = front == -1
}