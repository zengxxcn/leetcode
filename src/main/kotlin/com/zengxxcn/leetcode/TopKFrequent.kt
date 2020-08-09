package com.zengxxcn.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.Comparator

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

    fun topKFrequent2(nums: IntArray, k: Int): List<Int> {
        val map = mutableMapOf<Int, Int>()
        for (num in nums) {
            val frequency = map.computeIfAbsent(num) { 0 }
            map[num] = frequency + 1
        }
        val priorityQueue = PriorityQueue<Int>(Comparator { o1: Int, o2: Int ->
            map[o2]!! - map[o1]!!
        })
        map.forEach { priorityQueue.offer(it.key) }
        val list = mutableListOf<Int>()
        repeat (k) {
           list.add(priorityQueue.poll())
        }
        return list
    }

    @Test
    fun test() {
        assertEquals(listOf(1), topKFrequent2(listOf(1).toIntArray(), 1))
        assertEquals(listOf(1, 2), topKFrequent2(listOf(1,1,1,2,2,3).toIntArray(), 2))
    }
}