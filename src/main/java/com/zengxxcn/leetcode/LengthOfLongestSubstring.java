package com.zengxxcn.leetcode;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Problem 3: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {

    /*
     * User slide window, complexity O(n)
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0) return 0;

        int max = 0;
        int startPos = 0;

        HashMap<Character, Integer> charPositions = new HashMap<>();
        int pos = startPos;
        while (pos < len) {
            char ch = s.charAt(pos);
            Integer lastPos = charPositions.get(ch);
            if (lastPos != null && lastPos >= startPos) {
                int substrLen = pos - startPos;
                if (substrLen > max) max = substrLen;
                startPos = lastPos + 1;
            }
            charPositions.put(ch, pos);
            pos++;
        }

        int substrLen = pos - startPos;
        if (substrLen > max) max = substrLen;

        return max;
    }

    @Test
    public void test() {
        assertEquals(2, lengthOfLongestSubstring("abba"));
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        assertEquals(6, lengthOfLongestSubstring("abcdeg"));
        assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, lengthOfLongestSubstring("pwwkew"));
        assertEquals(0, lengthOfLongestSubstring(""));
        assertEquals(6, lengthOfLongestSubstring("abc123bs"));

    }
}
