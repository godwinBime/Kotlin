import java.lang.IndexOutOfBoundsException

class LinkedList<E : Any> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null


   private inner class Node<E> constructor(internal var element: E, internal var next: Node<E>?)

    fun addFirst(element: E){
        val h = head
        val newNode = Node<E>(element, h)
        head = newNode
        if (h == null){
            tail = newNode
        }
        size++
    }

    fun addLast(element: E){
        val t = tail
        val newNode = Node<E>(element, null)
        tail = newNode
        if (t == null){
            head = newNode
        }else{
            t.next = newNode
        }
        size++
    }

    fun add(index: Int, element: E){
        validatePositionIndex(index)
        if (index == 0)
            addFirst(element)
        else{
            var x = head
            val prevIndex = index - 1
            for(i in 0 until prevIndex){
                x = x!!.next
            }
            val next = x!!.next
            val newNode = Node(element, next)
            x.next = newNode
            size++
        }
    }

    fun getFirst() = head?.element

    fun getLast() = tail?.element

    fun get(index: Int): E{
        validateElementIndex(index)
        var x = head
        for (i in 0 until index){
            x = x!!.next
        }
        return x!!.element
    }

    fun set(index: Int, element: E):E{
        validateElementIndex(index)
        var x = head
        for (i in 0 until index){
            x = x!!.next
        }
        val oldVal = x!!.element
        x.element = element
        return oldVal
    }

    fun removeFirst(){
        head?.let {
            val next = it.next
            it.next = null
            head = next
            if (next == null){
                tail = null
            }
            size--
        }
    }

    fun removeLast(){
        tail?.let {
            val prev = getPrevious(it)
            tail = prev
            if (prev == null){
                head = null
            }else{
                prev.next = null
            }
            size--
        }
    }


    fun remove(index: Int){
        validateElementIndex(index)
        if (index == 0)
            removeFirst()
        else if (index == size - 1)
            removeLast()
        else{
            var x = head
            val previousIndex = index - 1
            for (i in 0 until previousIndex){
                if (i == index && x != null) {
                    unLink(x)
                }
                x = x!!.next
            }
        }
    }

    fun remove(element: E):Boolean{
        var curr = head
        while (curr != null){
            if (curr.element == element){
                unLink(curr)
                return true
            }
            curr = curr.next
        }
        return false
    }


    fun clear(){
        var x = head
        while (x != null){
            val next = x.next
            x.next = null
            x = next
        }
        tail = null
        head = tail
        size = 0
    }

    fun indexOf(element: E): Int{
        var index = 0
        var x = head
        while (x != null){
            if (element == x.element){
                return index
            }
            index++
            x = x.next
        }
        return -1
    }

    operator fun contains(element: E) = indexOf(element) != -1

    private fun unLink(curr: Node<E>):E{
        val element = curr.element
        val next = curr.next
        val prev = getPrevious(curr)
        if (prev == null){
            head = next
        }else{
            prev.next = next
            curr.next = null
        }
        if (next == null){
            prev?.next = null
            tail = prev
        }else{
            prev?.next = next
            curr.next = null
        }
        size--
        return element
    }

    private fun getPrevious(node: Node<E>): Node<E>?{
        if (head != null && node == null)
            return null
        var curr = head
        while (curr != null){
            if (curr.next == node){
                return curr
            }
            curr = curr.next
        }
        return null
    }

    private fun validatePositionIndex(index: Int){
        if (index < 0 || index > size)
            throw IndexOutOfBoundsException(outBoundsMessage(index))
    }

    private fun outBoundsMessage(index: Int): String{
        return "Index: $index, Size: $size"
    }

    private fun validateElementIndex(index: Int){
        if (index < 0 || index >= size){
            throw IndexOutOfBoundsException(outBoundsMessage(index))
        }
    }
}