class LinkedQueue<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    private inner class Node<E> constructor(internal var prev: Node<E>?, internal var element:E, internal var next: Node<E>?)

    fun enqueue(element:E){
        val t = tail
        val newNode = Node<E>(t, element, null)
        tail = newNode
        if (t == null)
            head = newNode
        else
            t.next = newNode
        size++
    }

    fun dequeue(): E{
        head?.let {
            val next = it.next
            it.next = null
            head = next
            if (next == null)
                tail = null
            else
                next.prev = null
            size--
            return it.element
        }?: throw FixedQueue.QueueUnderflowException()
    }

    fun front():E{
        head?.let {
            return it.element
        }?: throw FixedQueue.QueueUnderflowException()
    }

    fun rear():E{
        tail?.let {
            return it.element
        }?: throw FixedQueue.QueueUnderflowException()
    }

    fun isEmpty() = size == 0
}