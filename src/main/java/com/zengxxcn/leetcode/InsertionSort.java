package com.zengxxcn.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsertionSort {

    /**
     * Merge and insertion sort two sorted arrays
     * @return sorted array containing two input arrays
     */
    public int[] sortArrays(int[] nums1, int[] nums2) {
        int[] newNums = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            newNums[i] = nums1[i];
        }

        for (int j = 0; j < nums2.length; j++) {
            int k = nums1.length + j;
            while (k > 0 && newNums[k-1] > nums2[j]) {
                newNums[k] = newNums[k-1];
                k--;
            }
            newNums[k] = nums2[j];
        }
        return newNums;
    }

    private void assertArraysEqual(int[] nums1, int[] nums2) {
        assertEquals(nums1.length, nums2.length);
        for (int i = 0; i < nums1.length; i++) {
            assertEquals(nums1[i], nums2[i]);
        }
    }

    @Test
    public void test() {
        assertArraysEqual(new int[] {1, 2, 3, 9}, sortArrays(new int[] {1, 9}, new int[] {2, 3}));
        assertArraysEqual(new int[] {2, 3, 3, 4}, sortArrays(new int[] {2, 3, 4}, new int[] {3}));
    }
}
