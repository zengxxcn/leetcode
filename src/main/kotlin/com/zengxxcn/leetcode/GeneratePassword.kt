package com.zengxxcn.leetcode

import java.lang.StringBuilder

object PasswordGenerator {

    /**
     * Generate passwords by replacing some characters with provided replacements in all combinations.
     *
     * seed = "apple", replacements = { "a": ["@", "4"], "p": ["%"], "z": ["^"] }
     * returns = ["apple", "@pple", "4pple", "a%ple", "ap%le", "@%ple", "4%ple", "@p%le", "4p%le", "a%%le", "@%%le", "4%%le"]
     */
    fun generatePasswords(seed: String, replacements: Map<Char, List<String>>): List<String> {
        val indexMap = mutableMapOf<Int, Char>()
        seed.forEachIndexed { index, seedChar ->
            replacements.keys.forEach { replacement ->
                if (seedChar == replacement) {
                    indexMap[index] = seedChar
                }
            }
        }

        //exhaust all combinations of indexes
        val indexesList = generateSubLists(indexMap.keys.toList(), 0)
        println("Combinations: $indexesList")

        //generate password as per each combination
        val passwords = mutableListOf(seed)
        for (combination in indexesList) {
            val posReplacements = combination.map {
                val char = seed[it]
                replacements.getValue(char)
            }

            val replacementList = combine(posReplacements, 0)
            replacementList.forEach { replacementCombination ->
                val buf = StringBuilder(seed)
                combination.forEachIndexed { index, position ->
                    buf.replace(position, position+1, replacementCombination[index])
                }
                passwords.add(buf.toString())
            }
        }
        return passwords
    }


    private fun generateSubLists(input: List<Int>, index: Int): List<List<Int>> {
        if (index < input.size) {
            val value = input[index]
            val list = mutableListOf(listOf(value)) //its own
            val subCombinations = generateSubLists(input, index+1)
            list.addAll(subCombinations)
            subCombinations.forEach {
                val newList = mutableListOf(value)
                newList.addAll(it)
                list.add(newList)
            }
            return list
        } else {
            return emptyList()
        }
    }

    private fun combine(input: List<List<String>>, index: Int): List<List<String>> {
        if (index < input.size) {
            val values = input[index]
            val subCombined = combine(input, index+1)
            if (subCombined.isNotEmpty()) {
                val ret = mutableListOf<List<String>>()
                values.forEach { value ->
                    subCombined.forEach { subValues ->
                        val list = mutableListOf(value)
                        list.addAll(subValues)
                        ret.add(list)
                    }
                }
                return ret
            } else {
                return listOf(values)
            }
        } else {
            return emptyList()
        }

    }

}

fun main(args: Array<String>) {
    val pwds = PasswordGenerator.generatePasswords("apple",
            mapOf( 'a' to listOf("@", "4"), 'p' to listOf("%"), 'z' to listOf("^") ))
    println("Generated ${pwds.size} passwords: $pwds")
}