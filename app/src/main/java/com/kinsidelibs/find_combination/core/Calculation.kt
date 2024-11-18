package com.kinsidelibs.find_combination.core

class Calculation {

    fun findCombinations(list: List<Int>, target: Int) :List<List<Int>>{
        //sort list
        val nums = list.sortedDescending()
        //
        val bufferList = mutableListOf<Int>()
        val result = mutableListOf<List<Int>>()
        for (i in nums.indices) {
            bufferList.add(nums[i])
            if (i + 1 < nums.size) {
                for (j in i+1..<nums.size) {
                    bufferList.add(nums[j])
                    if (bufferList.sum() > target) { // value > target
                        bufferList.remove(bufferList.size-1)
                    } else if (bufferList.sum() == target) { // value == target
                        result.add(bufferList.toList())
                        bufferList.remove(bufferList.size-1)
                    }
                }
            }
            bufferList.clear()
        }
        return result
    }

}