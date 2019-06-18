package br.com.thoughtworks.plugin.strategy.shuffle

abstract class AbstractShuffleStrategy<T> {

    private var swapWithLast = -2

    fun shuffle(list: MutableList<T>) {
        if(!isPossible(list)) return
        keepShuffling(list)
    }

    private fun keepShuffling(list: MutableList<T>) {
        for (currentPos in 1 until list.size-1) {
            val current = list[currentPos]
            val previous = list[currentPos-1]

            if (equalsByCriteria(current, previous)) {
                val foundPosition = findNextDifferent(list, currentPos)
                swap(list, currentPos, foundPosition)
            }
        }

        if(swapLastIfNeeded(list)) keepShuffling(list)

    }

    private fun swapLastIfNeeded(list: MutableList<T>): Boolean {
        swapWithLast += 2
        val lastPosition = list.size - 1
        val swapNeeded = list.size > swapWithLast && equalsByCriteria(list[lastPosition], list[lastPosition - 1])
        if (swapNeeded) swap(list, swapWithLast, lastPosition)

        return swapNeeded
    }

    private fun swap(list: MutableList<T>, first: Int, last: Int) {
        val aux = list[first]
        list[first] = list[last]
        list[last] = aux
    }

    private fun findNextDifferent(list: List<T>, current: Int): Int {
        var position = current+1
        while (equalsByCriteria(list[position], list[current]) && position < list.size - 1) position++
        return position
    }

    protected abstract fun equalsByCriteria(left: T, right: T): Boolean

    fun isPossible(list: List<T>): Boolean {
        val groupOfRepeatingElements = list.groupBy { groupBy(it) }.filter { it.value.size > 1 }
        val maxRepeatingCount = groupOfRepeatingElements.maxBy { it.value.size }!!.value.size
        val differentElementsCount = list.toSet().size

        return differentElementsCount >= groupOfRepeatingElements.size &&
                maxRepeatingCount - differentElementsCount <= 1
    }

    abstract fun groupBy(it: T): Any
}