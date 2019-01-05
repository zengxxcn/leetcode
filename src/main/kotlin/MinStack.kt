import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

/*
 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.
 */

class MinStack() {

    /** initialize your data structure here. */
    private val list = ArrayList<Int>()
    private val treeMap = TreeMap<Int, ArrayList<Int>>()

    fun push(x: Int) {
        var indexList = treeMap[x]
        if (indexList == null) {
            indexList = ArrayList()
            treeMap[x] = indexList
        }
        indexList.add(list.size)

        list.add(x)
    }

    fun pop() {
        if(list.size > 0) {
            val index = list.size - 1
            val x = list.removeAt(index)
            val indexList = treeMap[x]
            indexList!!.remove(index)
            if(indexList.isEmpty()) {
                treeMap.remove(x)
            }
        }
    }

    fun top(): Int? {
        return if (list.size > 0) list[list.size - 1] else null
    }

    fun getMin(): Int? {
        return if (treeMap.size > 0) treeMap.firstKey() else return null
    }

}

class MinStack2() {

    /** initialize your data structure here. */
    private val dataStack = Stack<Int>()
    private val minStack = Stack<Int>()

    fun push(x: Int) {
        dataStack.push(x)
        if (minStack.empty())
            minStack.push(x)
        else {
            val min = minStack.peek()
            minStack.push(if (min < x) min else x)
        }
    }

    fun pop() {
        dataStack.pop()
        minStack.pop()
    }

    fun top(): Int {
        return dataStack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
 class MinStackTest {

    @Test
    fun test1() {
        var stack = MinStack2()
        stack.push(0)
        stack.push(1)
        stack.push(0)
        assertEquals(0, stack.getMin())
        stack.pop()
        assertEquals(0, stack.getMin())
    }

    @Test
    fun test2() {
        var stack = MinStack2()
        stack.push(-2)
        stack.push(0)
        stack.push(-3)
        assertEquals(-3, stack.getMin())
        stack.pop()
        assertEquals(0, stack.top())
        assertEquals(-2, stack.getMin())
    }
}