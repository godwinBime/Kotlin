import java.lang.IllegalArgumentException

class HashMap<K, V> {
    private val minCapacity = 1 shl 4
    private val maxCapacity = 1 shl 30
    private var table: Array<Node<K, V>?>
    var size = 0
        private set
    constructor(){
        this.table = arrayOfNulls(minCapacity)
    }

    constructor(capacity: Int){
        if (capacity < 0)
            throw IllegalArgumentException("Invalid Capacity: $capacity")
        val finalCapacity = when{
            capacity < minCapacity -> minCapacity
            capacity > maxCapacity -> maxCapacity
            else -> fetchNearestCapacity(capacity)
        }
        this.table = arrayOfNulls(finalCapacity)
    }

    private fun hash(key: K): Int{
        val h = key?.hashCode() ?: 0
        return h xor (h ushr 16)
    }

    fun getBucketIndex(key: String) = key[0].toUpperCase() - 'A'

    //if hashMap size is fixed at 100
    //fun getBucketIndex(key: K) = key.hashCode() % 100

    fun getBucketIndex(key: K){
        val hash = hash(key)
        val lastIndex = table.size - 1
        val bucketIndex = lastIndex and hash
    }

    fun put(key: K, value: V){
        putVal(key, value)
    }

    fun putIfAbsent(key: K, value: V){
        putVal(key, value, true)
    }

    private fun putVal(key: K, value: V, onlyAbsent: Boolean = false){
        val hash = hash(key)
        val n = table.size
        val index = (n - 1) and hash
        var first = table[index]
        if (first == null){
            table[index] = Node(hash, key, value, null)
            ++size
        }else{
            var node: Node<K, V>?
            var k = first.key
            if (first.hash == hash && (k === key || k == key) && !onlyAbsent)
                first.value = value
            else{
                while (true){
                    node = first!!.next
                    if (node == null){
                        first.next = Node(hash, key, value, null)
                        break
                    }
                    k = node.key
                    if (node.hash == hash && (k == key || k == key) && !onlyAbsent){
                        node.value = value
                        break
                    }
                    first = node
                }
            }
        }
    }

    fun get(key: K): V?{
        val e = getNode(hash(key), key)
        return if (e == null) null else e.value
    }

    fun remove(key: K): V?{
        val hash = hash(key)
        val n = table.size
        val index = (n - 1) and hash
        var first = table[index]
        if (n > 0 && first != null){
            var node: Node<K, V>? = null
            var k = first.key
            if (first.hash == hash && (key === k || key == k))
                node = first
            else{
                var nextNode = first.next
                if (nextNode != null){
                    do {
                        k = nextNode!!.key
                        if (nextNode.hash == hash && (key == k || key == k)){
                            node = nextNode
                            break
                        }
                        first = nextNode
                        nextNode = nextNode.next
                    }while (nextNode != null)
                }
            }
            if (node != null){
                if (node == first)
                    table[index] = node.next
                else
                    first!!.next = node.next
                --size
                return node.value
            }
        }
        return null
    }

    private fun getNode(hash: Int, key: K): Node<K, V>?{
        val n = table.size
        if (n > 0){
            val first = table[(n - 1) and hash]
            if (first != null){
                if (first.hash == hash){//checking the first node
                    val k = first.key
                    if (k === key || k == key)
                        return first
                }
                var e = first.next ?: return  null
                do {
                    if (e.hash == hash && e.key === key || e.key == key)
                        return e
                }while (e.next != null)
            }
        }
        return null
    }

    private fun fetchNearestCapacity(i: Int): Int{
        var retVal = i - 1 //if input is a power of 2, shift its high-order bit right

        //Smear the high-order bit all the way to the right
        retVal = retVal or retVal ushr 1
        retVal = retVal or retVal ushr 2
        retVal = retVal or retVal ushr 4
        retVal = retVal or retVal ushr 8
        retVal = retVal or retVal ushr 16
        return retVal + 1
    }

    private class Node<K, V>(val hash: Int, val key: K, var value: V, var next: Node<K, V>?){
        override fun toString() = "$key = $value"
        override fun hashCode() = (key?.hashCode() ?: 0).xor(value?.hashCode() ?: 0)

        override fun equals(other: Any?): Boolean{
            if (other === this)
                return true
            if (other is Node<*, *> && this.key == other.key && this.value == other.value)
                return true
            return false
        }
    }
}
