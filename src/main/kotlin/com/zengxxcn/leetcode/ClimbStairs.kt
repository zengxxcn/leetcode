package com.zengxxcn.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * https://leetcode.com/problems/climbing-stairs/
 */
class ClimbStairs {
    fun climbStairs(n: Int): Int {
        return when (n) {
            1 -> 1
            2 -> 2
            else -> {
                climbStairs(n - 2) + climbStairs(n - 1)
            }
        }

    }

    @Test
    fun test() {
        assertEquals(3, climbStairs(3))
        assertEquals(5, climbStairs(4))
        assertEquals(8, climbStairs(5))
    }
}