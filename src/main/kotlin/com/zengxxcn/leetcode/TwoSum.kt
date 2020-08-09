package com.zengxxcn.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * https://leetcode.com/problems/two-sum/
 */
class TwoSum {
    
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val num2IndexMap = nums.mapIndexed { index, num -> num to index }.toMap()
        for (index in 0 until nums.size) {
            val other = target - nums[index]
            val otherIndex = num2IndexMap.get(other)
            if (otherIndex != null && otherIndex != index)
                return intArrayOf(index, otherIndex)
        }
        return intArrayOf(-1, -1)
    }

    @Test
    fun test() {
        val nums = intArrayOf(3, 5, 9, 17)
        val target = 22
        val result = TwoSum().twoSum(nums, target)
        assertTrue(result.contains(1))
        assertTrue(result.contains(3))
    }
}


