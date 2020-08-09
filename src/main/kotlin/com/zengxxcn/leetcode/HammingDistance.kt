package com.zengxxcn.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * https://leetcode.com/problems/hamming-distance/
 */
class HammingDistance {

    fun hammingDistance(x: Int, y: Int): Int {
        var distance = 0
        for (i in 0..31) {
            distance += ((x shr i) xor (y shr i)).rem(2)
        }
        return distance
    }

    @Test
    fun test() {
        assertEquals(2, hammingDistance(1, 4))
        assertEquals(1, hammingDistance(3, 1))
        assertEquals(31, hammingDistance(0, Integer.MAX_VALUE))
    }
}