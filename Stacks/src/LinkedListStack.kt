class LinkedListStack<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    private inner class Node<E> constructor(internal var prev: Node<E>?, internal var element: E, internal var next: Node<E>?)

    fun push(element: E){
        val t = tail
        val newNode = Node<E>(t, element, null)
        tail = newNode
        if (t == null){
            head = newNode
        }else{
            t.next = newNode
        }
        size++
    }

    fun pop():E {
        tail?.let {
            val prev = it.prev
            it.prev = null
            tail = prev
            if (prev == null)
                head = null
            else
                prev.next = null
            size--
            return it.element
        }?:throw Stack.StackUnderflowException()
    }

    fun peek(): E{
        tail?.let {
            return it.element
        }?: throw Stack.StackUnderflowException()
    }

    fun isEmpty() = size == 0
}