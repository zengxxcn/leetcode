package com.zengxxcn.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BinarySearch {

    fun search(nums: IntArray, target: Int): Int {
        return binarySearch(nums, target, 0, nums.size - 1)
    }

    fun binarySearch(nums: IntArray, target: Int, startIndex: Int, endIndex: Int): Int {
        if (startIndex == endIndex)
            return if (nums[startIndex] == target) startIndex else -1

        val midIndex = startIndex + (endIndex - startIndex) / 2

        val midValue = nums[midIndex]
        return when {
            midValue == target -> midIndex
            midValue > target -> if (midIndex == startIndex) -1 else binarySearch(nums, target, startIndex, midIndex - 1)
            else -> if (midIndex == endIndex) -1 else binarySearch(nums, target, midIndex + 1, endIndex)
        }
    }

    @Test
    fun test1() {
        assertEquals(search(intArrayOf(-1, 0, 3, 5, 9, 12), 9), 4)
        assertEquals(search(intArrayOf(-1, 0, 3, 5, 9, 12), 2), -1)
        assertEquals(search(intArrayOf(2, 5), 0), -1)
    }
}