package com.zengxxcn.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
class TopKFrequent {
    data class Item(val num: Int, val count: Int): Comparable<Item> {
        override fun compareTo(other: Item): Int {
            return count - other.count
        }
    }

    fun topKFrequent(nums: IntArray, k: Int): List<Int> {
        val map = mutableMapOf<Int, Item>()
        for (num in nums) {
            val item = map.computeIfAbsent(num) { Item(num, 0) }
            map[num] = Item(num, item.count + 1)
        }

        return map.values.sortedDescending()
                .subList(0, k)
                .map { it.num }
    }

    @Test
    fun test() {
        assertEquals(listOf(1), topKFrequent(listOf(1).toIntArray(), 1))
        assertEquals(listOf(1, 2), topKFrequent(listOf(1,1,1,2,2,3).toIntArray(), 2))
    }
}