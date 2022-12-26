import kotlin.collections.Collection

//LinearSearch in an unordered list
fun <E> Collection<E>.linearSearch(element: E): Int{
    for((index, value) in this.withIndex()){
        if (value == element)
            return index
    }
    return -1
}

fun main(){
    val languages = arrayListOf("Python", "java", "Kotlin", "Scala")
    println("Kotlin is at - ${languages.linearSearch("Kotlin")}")
}

//LinearSearch in an ordered list
fun <E: Comparable<E>> Collection<E>.searchInSortedCollection(element: E): Int{
    for((index, value) in this.withIndex()){
        if (value == element)
            return index
        else if (value > element)
            return -1
    }
    return -1
}

//Binary search ordered list
fun <E: Comparable<E>> List<E>.binarySearch(element: E): Int{
    var left = 0
    var right = size -1
    while (left <= right){
        var mid = (left + right) / 2
        val midVal = this[mid]
        val compare = midVal.compareTo(element)

        if (compare < 0)
            left = mid + 1
        else if (compare > 0)
            right = mid - 1
        else
            return mid //element found
    }
    return -1 //element not found
}

/**
 * f(n) = m/n + (m - 1) = n + m^2 - m = sqr(n)
 * **/
fun<E: Comparable<E>> Array<E>.jumpSearch(element: E): Int{
    val size = this.size
    var step = Math.sqrt(size.toDouble()).toInt()
    var prev = 0

    while (this[Math.min(step, size) - 1] < element){
        prev = step
        step *= 2
        if (prev >= size.toInt())
            return -1
    }

    while (this[prev] < element){
        prev++
        if (prev == Math.min(step, size))
            return -1
    }

    if (this[prev] == element)
        return prev

    return -1
}

/**Naive pattern search**/
fun search(text: String, pattern: String): Int{
    var retVal = -1
    val patternLen = pattern.length
    val len = text.length - patternLen
    for (i in 0 until len){
        var isFound = true
        for (j in 0 until patternLen){
            if (text[i + j] != pattern[j]){
                isFound = false
                break
            }
        }
        if (isFound){
            retVal = i
            break
        }
    }
    return retVal
}

/**Rabin-Karp hash function**/
private fun hash(input: String): Long{
    var result = 0L
    input.forEachIndexed { index, char ->
            result += (char.toDouble() * Math.pow(97.0, index.toDouble())).toLong()
        }
        return result
    }

/**Rolled hash function for Rabin-Karp**/
private fun rolledHash(oldChar: Char, newChar: Char, oldHash: Long, patternLen: Int): Long{
    val newHash = (((oldHash - oldChar.toLong())/ 97) + newChar.toDouble() * Math.pow(97.0, (patternLen - 1).toDouble
    ())).toLong()
    return newHash
}

/**Searching using hash values**/
fun searchUsingHashValues(text: String, pattern: String): Int{
    val patternLen = pattern.length
    val textLen = text.length - patternLen
    val patternHash = hash(pattern)
    var subText = text.substring(0, patternLen)
    var subTextHash = hash(subText)
    var isFound = false

    if((patternHash == subTextHash) and subText.equals(pattern))
        return 0

    for (i in 1..textLen){
        subTextHash = rolledHash(text[i - 1], text[i + patternLen - 1], subTextHash, patternLen)
        if ((patternHash == subTextHash) and text.substring(i, i + patternLen).equals(pattern))
            return i
    }
    return -1
}

/**The Knuth-Morris-Pratt algorithm
 *
 * The prefix function...**/
fun preparePrefixArray(pattern: String): IntArray{
    val patternLen = pattern.length
    val arr = IntArray(patternLen)
    var index = 0
    var i = 1

    while (i < patternLen)
        if (pattern[i] == pattern[index]){
            arr[i] = index +  1
            index++
            i++
        }else{
            if (index != 0)
                index = arr[index - 1]
            else{
                arr[i] = 0
                i++
            }
        }
    return arr
}

/**Search using prefix array**/
fun searchUsingPrefixArray(text: String, pattern: String): Int{
    val prefixArr = preparePrefixArray(pattern)
    val textLen = text.length
    val patternLen = pattern.length

    var patternIndex = 0
    var textIndex = 0

    while ((textIndex < textLen) and (patternIndex < patternLen)){
        if (pattern[patternIndex] == text[textIndex]){
            textIndex++
            patternIndex++
        }else{
            //Use the prefix array to skip few indexes
            if (patternIndex != 0)
                patternIndex = prefixArr[patternIndex - 1]
            else
                textIndex++
        }

        if (patternIndex == patternLen){
            //We found the pattern
            return textIndex - patternIndex
        }
    }
    return -1
}
