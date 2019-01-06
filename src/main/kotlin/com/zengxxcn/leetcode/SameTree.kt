package com.zengxxcn.leetcode

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Definition for a binary tree node.
 */

class TreeNode(var `val`: Int = 0) {
     var left: TreeNode? = null
     var right: TreeNode? = null
}

class SameTree {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null)
            return true
        else if (p != null && q == null)
            return false
        else if (p == null && q != null)
            return false
        else {
            if (p!!.`val` != q!!.`val`)
                return false
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }

    @Test
    fun test1() {
        val tree1 = TreeNode(1)
        tree1.left = TreeNode(2)
        tree1.right = TreeNode(3)

        val tree2 = TreeNode(1)
        tree2.left = TreeNode(2)
        tree2.right = TreeNode(3)

        assertTrue(isSameTree(tree1, tree2))
    }

    @Test
    fun test2() {
        val tree1 = TreeNode(1)
        tree1.left = TreeNode(2)

        val tree2 = TreeNode(1)
        tree2.right = TreeNode(2)

        assertFalse(isSameTree(tree1, tree2))
    }

    @Test
    fun test3() {
        val tree1 = TreeNode(1)
        tree1.left = TreeNode(2)
        tree1.right = TreeNode(1)

        val tree2 = TreeNode(1)
        tree2.left = TreeNode(1)
        tree2.right = TreeNode(2)

        assertFalse(isSameTree(tree1, tree2))
    }
}
