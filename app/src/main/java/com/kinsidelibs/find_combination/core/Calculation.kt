package com.kinsidelibs.find_combination.core

import android.util.Log

class Calculation {
    companion object {
        fun findCombinations(list: List<Int>, target: Int): List<List<Int>> {
            //sort list
            val nums = list.sortedDescending()
            //
            val bufferList = mutableListOf<Int>()
            val result = mutableListOf<List<Int>>()
            for (i in nums.indices) {
                bufferList.add(nums[i])
                if (i + 1 < nums.size) {
                    for (j in i + 1..<nums.size) {
                        bufferList.add(nums[j])
                        if (bufferList.sum() > target) { // value > target
                            bufferList.removeAt(bufferList.lastIndex)
                        } else if (bufferList.sum() == target) { // value == target
                            result.add(bufferList.toList())
                            bufferList.removeAt(bufferList.lastIndex)
                        }
                    }
                }
                bufferList.clear()
            }
//            Log.d("debug" , list.toString())
//            Log.d("debug" , result.toString())
            return result
        }
    }

}