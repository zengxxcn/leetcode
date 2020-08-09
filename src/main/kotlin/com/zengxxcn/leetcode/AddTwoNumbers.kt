package com.zengxxcn.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Problem 2: https://leetcode.com/problems/add-two-numbers/
 */
class AddTwoNumbers {

    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null

        fun toLong(): Long {
            var i = 0
            var n = 0L
            var base = 0
            var p: ListNode? = this
            while(p != null) {
                base = if (base == 0) 1 else base * 10
                n += p.`val` * base
                p = p.next
                i++
            }
            return n
        }

        companion object {
            fun fromLong(n: Long): ListNode {
                var current = n
                val head = ListNode()
                var p = head
                while(true) {
                    val remain = (current % 10).toInt()
                    current /= 10
                    p.next = ListNode(remain)
                    p = p.next!!
                    if (current == 0L) break
                }
                return head.next!!
            }
        }

    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        return addTwo(l1, l2)
    }

    fun addTwo(l1: ListNode?, l2: ListNode?, addition: Int = 0): ListNode? {
        if (l1 == null && l2 == null) return if (addition > 0) { ListNode(addition) } else null
        else if (l1 == null) {
            return addTwo(ListNode(addition), l2)
        }
        else if (l2 == null) {
            return addTwo(l1, ListNode(addition))
        }
        else {
            val sum = l1.`val` + l2.`val` + addition
            val result = ListNode(sum % 10)
            result.next = addTwo(l1.next, l2.next, sum/10)
            return result
        }
    }

    fun plusTwoNumbers(a: Long, b: Long): Long {
        return addTwoNumbers(ListNode.fromLong(a), ListNode.fromLong(b))!!.toLong()
    }

    @Test
    fun test() {
        assertEquals(807, plusTwoNumbers(342, 465))
        assertEquals(8, plusTwoNumbers(0, 8))
        assertEquals(108, plusTwoNumbers(100, 8))
        assertEquals(1198, plusTwoNumbers(99, 1099))
    }
}

