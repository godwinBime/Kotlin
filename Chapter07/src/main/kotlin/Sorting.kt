/**Bubble sort**/
fun <E: Comparable<E>> MutableList<E>.bubbleSort(){
    val len = size
    for (i in 0 until (len - 1)){
        for (j in 0 until (len - i - 1)){
            if (this[j].compareTo(this[j + 1]) > 0){
                val temp = this[j]
                this[j] = this[j + 1]
                this[j + 1] = temp
            }
        }
    }
}


/**Selection Sort**/
fun <E: Comparable<E>> MutableList<E>.selectionSort(){
    val len = size
    //Find the minimum value of the array
    for (i in 0 until (len - 1)){
        //Getting the index where minimum value is present
        var minIndex = i
        for (j in (i + 1) until len){
            if (this[j].compareTo(this[minIndex]) < 0)
                minIndex = j
        }
        //We got the minimum element, now swap that to the first element
        val temp = this[minIndex]
        this[minIndex] = this[i]
        this[i] = temp
    }
}

/**Insertion sort**/
fun <E: Comparable<E>> MutableList<E>.insertionSort(){
    val len = size
    for (i in 1 until len){
        var key = this[i]
        var j = i - 1
        while (j >= 0 && this[j].compareTo(key) > 0){
            this[j + 1] = this[j]
            j--
        }
        this[j + 1] = key
    }
}

/**Merge sort**/
fun <E: Comparable<E>> Array<E>.splitArray(): Array<E>{
    if(size <= 1)
        return this

    val middle = size / 2
    val left = copyOfRange(0, middle)
    val right = copyOfRange(middle, size)
    return merge(this, left.splitArray() , right.splitArray())
}

private fun <E: Comparable<E>> merge(arr: Array<E>, left: Array<E>, right: Array<E>): Array<E>{
    val leftArrSize = left.size
    val rightArrSize = right.size
    var leftArrIndex = 0
    var rightArrIndex = 0
    var index = 0

    while (leftArrIndex < leftArrSize && rightArrIndex < rightArrSize){
        if (left[leftArrIndex] <= right[rightArrIndex]){
            arr[index] = left[leftArrIndex]
            leftArrIndex++
        }else{
            arr[index] = right[rightArrIndex]
            rightArrIndex++
        }
        index++
    }

    while (leftArrIndex < leftArrSize){
        arr[index] = left[leftArrIndex]
        leftArrIndex++
        index++
    }

    while (rightArrIndex < rightArrSize){
        arr[index] = right[rightArrIndex]
        rightArrIndex++
        index++
    }
    return arr
}

/**Quick sort **/
fun <E: Comparable<E>> Array<E>.sort(){
    sort(this, 0, size - 1)
}

private fun <E: Comparable<E>> sort(arr: Array<E>, low: Int, high: Int){
    if (low < high){
        val partitionIndex = partition(arr, low, high)
        sort(arr, low, partitionIndex - 1)
        sort(arr, partitionIndex + 1, high)
    }
}

private fun <E: Comparable<E>> partition(arr: Array<E>, low: Int, high: Int): Int{
    val pivot = arr[high]
    var i = low - 1
    for (j in low until high){
        if (arr[j] <= pivot){
            i++
            arr[i] = arr[j].also { arr[j] = arr[i] }
        }
    }
    arr[i + 1] = arr[high].also { arr[high] = arr[i + 1] }
    return i + 1
}

/**Heap sort **/
fun <E: Comparable<E>> Array<E>.heapSort(){
    val middle = size / 2 - 1
    for (i in middle downTo 0){
        heapify(this, size, i)
    }

    for (i in size - 1 downTo 0){
        this[0] = this[i].also { this[i] = this[0] }
        heapify(this, i, 0)
    }
}

private fun <E: Comparable<E>> heapify(arr: Array<E>, heapSize: Int, root: Int){
    var largest = root
    val leftNode = 2 * root + 1
    val rightNode = 2 * root + 2

    if (leftNode < heapSize && arr[leftNode] > arr[largest])
        largest = leftNode
    if (rightNode < heapSize && arr[rightNode] > arr[largest])
        largest = rightNode
    if (largest != root){
        arr[root] = arr[largest].also { arr[largest] = arr[root] }
        heapify(arr, heapSize, largest)
    }
}