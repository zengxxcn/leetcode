package com.zengxxcn.leetcode

import org.junit.Assert
import org.junit.Test

class PasswordGenerator {

    /**
     * Generate passwords by replacing some characters with provided replacements in all combinations.
     *
     * seed = "apple", replacements = { "a": ["@", "4"], "p": ["%"], "z": ["^"] }
     * returns = ["apple", "@pple", "4pple", "a%ple", "ap%le", "@%ple", "4%ple", "@p%le", "4p%le", "a%%le", "@%%le", "4%%le"]
     */
    fun genPasswd(seed: String, replacements: Map<Char, List<String>>): List<String> {
        val strList = expandSeedWithReplacements(seed, replacements)
        return combine(strList, 0)
    }

    /**
     * Expand every seed character to a list of candidate strings.
     * e.g.
     * 'a' -> "a", "@", "4"
     * 'p' -> "p", "%"
     * 'p' -> "p", "%"
     * 'l' -> "l"
     * 'e' -> "e"
     */
    private fun expandSeedWithReplacements(seed: String, replacements: Map<Char, List<String>>): List<List<String>> {
        return seed.map {
            val strList = mutableListOf(it.toString())
            replacements[it]?.forEach { replacement ->
                strList.add(replacement)
            }
            strList.toList()
        }
    }

    /**
     * Generate all combinations of possible strings.
     *
     * It goes depth-first traverse recursively.
     *
     * @param input list of List<String>
     * e.g.
     * [0]: "a", "@", "4"
     * [1]: "p", "%"
     * [2]: "p", "%"
     * [3]: "l"
     * [4]: "e"
     *
     * @param index start from
     *
     * @return all combination of strings from index
     * e.g.
     * index = 4: ["e"]
     * index = 3: ["le"]
     * index = 2: ["ple", "%le"]
     * index = 1: ["pple", "p%le", "%ple", "%%le"]
     * index = 0: ["apple", "ap%le", "a%ple", "a%%le", "@pple", "@p%le", "@%ple", "@%%le", "4pple", "4p%le", "4%ple", "4%%le"]
     */
    private fun combine(input: List<List<String>>, index: Int): List<String> {
        if (index < input.size - 1) {
            val currCandidates = input[index]
            val subCombined = combine(input, index + 1)
            val ret = mutableListOf<String>()
            currCandidates.forEach { value ->
                subCombined.forEach { subValues ->
                    ret.add(value + subValues)
                }
            }
            return ret
        } else {
            // index == input.size - 1
            return input[index]
        }
    }

    @Test
    fun test() {
        val pwds = genPasswd("apple", mapOf( 'a' to listOf("@", "4"), 'p' to listOf("%"), 'z' to listOf("^") ))
        println("Generated ${pwds.size} passwords: $pwds")
        Assert.assertEquals(3 * 2 * 2 * 1 * 1, pwds.size)
    }

}
