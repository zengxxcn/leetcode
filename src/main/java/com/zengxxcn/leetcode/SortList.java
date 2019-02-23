package com.zengxxcn.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 148 - https://leetcode.com/problems/sort-list/
 * Using merge sort - bottom up
 */
public class SortList {
    public static final class ListNode {
        ListNode next;
        int val;
        
        public final ListNode append(ListNode n) {
            ListNode tail = this;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = n;
            return this;
        }

        public ListNode(int val) {
            this.val = val;
        }
    }
    
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = head;
        int size = 1;
        while(true) {
            Object[] result = mergeSort(newHead, size);
            newHead = (ListNode) result[0];
            if ((Integer) result[1] == 1) break;  // merged only once, which should be last one
            size = size * 2;
        }

        return newHead;
    }

    public Object[] mergeSort(ListNode head, int size) {
        if (head == null) return new Object[] { null, 0};
        if (head.next == null) return new Object[] { head, 1 };

        int count = 0;
        ListNode newHead = null;
        ListNode nextHead = head;
        ListNode newTail = null;

        while (nextHead != null) {
            int leftNum = 0;
            ListNode leftHead = nextHead;
            ListNode leftNext = nextHead;
            while (leftNext != null) {
                leftNum++;
                if (leftNum < size) leftNext = leftNext.next;
                else break;
            }

            if (leftNext == null || leftNext.next == null) {
                // no right side, just return the left side that has been sorted
                if (newHead == null) newHead = leftHead;
                if (newTail != null) newTail.next = leftHead;
                nextHead = null;
            } else {
                ListNode rightHead = leftNext.next;
                ListNode rightNext = rightHead;
                int rightNum = 0;
                while (rightNext != null) {
                    rightNum++;
                    if (rightNum < size) rightNext = rightNext.next;
                    else break;
                }
                nextHead = rightNext != null ? rightNext.next : null;

                //merge left and right
                ListNode[] merged = merge(leftHead, leftNext, rightHead, rightNext);

                if (newHead == null) newHead = merged[0];       // set the head if it's the first sub list
                if (newTail != null) newTail.next = merged[0];  // connect previous list to this one
                newTail = merged[1];
            }
            count++;
        }

        return new Object[] { newHead, count };
    }

    public ListNode[] merge(ListNode leftHead, ListNode leftTail, ListNode rightHead, ListNode rightTail) {
        ListNode head = null;
        ListNode tail = null;
        ListNode currLeft = leftHead;
        ListNode currRight = rightHead;

        boolean leftDone = false;
        boolean rightDone = false;
        while (!(leftDone && rightDone)) {
            boolean moveLeft = false;
            boolean moveRight = false;
            if (leftDone)  moveRight = true;
            else if (rightDone) moveLeft = true;
            else if (currLeft.val >= currRight.val) moveRight = true;
            else if (currLeft.val < currRight.val) moveLeft = true;

            if (moveLeft) {
                if (tail == null) tail = currLeft;
                else {
                    tail.next = currLeft;
                    tail = currLeft;
                }
                if (currLeft == leftTail) leftDone = true;
                else currLeft = currLeft.next;
            }
            else if (moveRight) {
                if (tail == null) tail = currRight;
                else {
                    tail.next = currRight;
                    tail = currRight;
                }
                if (currRight == rightTail) rightDone = true;
                else {
                    currRight = currRight.next;
                    if (currRight == null) rightDone = true;
                }
            }
            if (head == null) head = tail;
        }

        return new ListNode[] { head, tail };
    }

    @Test
    public void test1() {
        ListNode list = new ListNode(4);
        list.append(new ListNode(2))
                .append(new ListNode(1))
                .append(new ListNode(3));
        ListNode sorted = sortList(list);
        assertEquals(1, sorted.val);
        assertEquals(2, sorted.next.val);
        assertEquals(3, sorted.next.next.val);
        assertEquals(4, sorted.next.next.next.val);
    }
    
    @Test
    public void test2() {
        ListNode list = new ListNode(-1);
        list.append(new ListNode(5))
                .append(new ListNode(3))
                .append(new ListNode(4))
                .append(new ListNode(0));
        ListNode sorted = sortList(list);
        assertEquals(-1, sorted.val);
        assertEquals(0, sorted.next.val);
        assertEquals(3, sorted.next.next.val);
        assertEquals(4, sorted.next.next.next.val);
        assertEquals(5, sorted.next.next.next.next.val);
    }

    @Test
    public void test3() {
        ListNode list = new ListNode(4);
        list.append(new ListNode(2));
        ListNode sorted = sortList(list);
        assertEquals(2, sorted.val);
        assertEquals(4, sorted.next.val);
    }


    @Test
    public void test4() {
        ListNode list = new ListNode(1);
        list.append(new ListNode(2))
                .append(new ListNode(3))
                .append(new ListNode(4))
                .append(new ListNode(5));
        ListNode sorted = sortList(list);
        assertEquals(1, sorted.val);
        assertEquals(2, sorted.next.val);
        assertEquals(3, sorted.next.next.val);
        assertEquals(4, sorted.next.next.next.val);
        assertEquals(5, sorted.next.next.next.next.val);
    }

    @Test
    public void test5() {
        ListNode list = new ListNode(5);
        list.append(new ListNode(4))
                .append(new ListNode(3))
                .append(new ListNode(2))
                .append(new ListNode(1));
        ListNode sorted = sortList(list);
        assertEquals(1, sorted.val);
        assertEquals(2, sorted.next.val);
        assertEquals(3, sorted.next.next.val);
        assertEquals(4, sorted.next.next.next.val);
        assertEquals(5, sorted.next.next.next.next.val);
    }
}
