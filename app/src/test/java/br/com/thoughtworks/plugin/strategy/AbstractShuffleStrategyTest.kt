package br.com.thoughtworks.plugin.strategy

import br.com.thoughtworks.plugin.strategy.shuffle.AbstractShuffleStrategy
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

open class AbstractShuffleStrategyTest {
    protected lateinit var shuffler: Shuffler

    @Before
    fun setup() {
        shuffler = Shuffler()
    }

    class ArrangementTypeABC: AbstractShuffleStrategyTest() {
        @Test //2-1-1
        fun givenAnyArrangementOf_2_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //2-2-1
        fun givenAnyArrangementOf_2_2_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,2,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //2-2-2
        fun givenAnyArrangementOf_2_2_2_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,2,2,3,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //3-1-1
        fun givenAnyArrangementOf_3_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //3-2-1
        fun givenAnyArrangementOf_3_2_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,2,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //3-3-1
        fun givenAnyArrangementOf_3_3_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,2,2,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //3-3-2
        fun givenAnyArrangementOf_3_3_2_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,2,2,2,3,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //3-3-3
        fun givenAnyArrangementOf_3_3_3_repetitions_thenShuffle() {
            val list = mutableListOf(2,2,2,1,1,1,3,3,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-1-1
        fun givenAnyArrangementOf_4_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-2-1
        fun givenAnyArrangementOf_4_2_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-3-1
        fun givenAnyArrangementOf_4_3_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-1
        fun givenAnyArrangementOf_4_4_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-2
        fun givenAnyArrangementOf_4_4_2_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-3
        fun givenAnyArrangementOf_4_4_3_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3,3,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-4
        fun givenAnyArrangementOf_4_4_4_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3,3,3,3)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }
    }

    class ArrangementTypeABCD: AbstractShuffleStrategyTest() {
        @Test //4-1-1-1
        fun givenAnyArrangementOf_4_1_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-2-1-1
        fun givenAnyArrangementOf_4_2_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-3-1-1
        fun givenAnyArrangementOf_4_3_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-1-1
        fun givenAnyArrangementOf_4_4_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-2-2-1
        fun givenAnyArrangementOf_4_2_2_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-2-3-1
        fun givenAnyArrangementOf_4_2_3_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,3,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-2-4-1
        fun givenAnyArrangementOf_4_2_4_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,3,3,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-3-4-1
        fun givenAnyArrangementOf_4_3_4_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,3,3,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-4-1
        fun givenAnyArrangementOf_4_4_4_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3,3,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-4-2
        fun givenAnyArrangementOf_4_4_4_2_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3,3,3,3,4,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-4-3
        fun givenAnyArrangementOf_4_4_4_3_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //4-4-4-4
        fun givenAnyArrangementOf_4_4_4_4_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-1-1-1
        fun givenAnyArrangementOf_5_1_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-2-1-1
        fun givenAnyArrangementOf_5_2_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-3-1-1
        fun givenAnyArrangementOf_5_3_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,2,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-4-1-1
        fun givenAnyArrangementOf_5_4_1_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,2,2,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-2-2-1
        fun givenAnyArrangementOf_5_2_2_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-2-3-1
        fun givenAnyArrangementOf_5_2_3_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,3,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-2-4-1
        fun givenAnyArrangementOf_5_2_4_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,3,3,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-3-4-1
        fun givenAnyArrangementOf_5_3_4_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,2,3,3,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-4-4-1
        fun givenAnyArrangementOf_5_4_4_1_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,2,2,3,3,3,3,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-4-4-2
        fun givenAnyArrangementOf_5_4_4_2_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,2,2,3,3,3,3,4,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-4-4-3
        fun givenAnyArrangementOf_5_4_4_3_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }

        @Test //5-4-4-4
        fun givenAnyArrangementOf_5_4_4_4_repetitions_thenShuffle() {
            val list = mutableListOf(1,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4)
            shuffler.shuffle(list)
            assertTrue(isShuffled(list))
        }
    }

    protected class Shuffler : AbstractShuffleStrategy<Int>() {
        override fun groupBy(it: Int): Any {
            return it
        }

        override fun equalsByCriteria(left: Int, right: Int): Boolean {
            return left == right
        }
    }

    protected fun isShuffled(list: List<Int>): Boolean {
        for(i in 1 until list.size-1) {
            if(list[i] == list[i-1]) return false
        }
        return true
    }
}