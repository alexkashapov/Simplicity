package com.fake.simplicity.data

import com.fake.simplicity.utils.MAX_ELEMENTS_COUNT
import com.fake.simplicity.utils.MIN_ELEMENTS_COUNT
import com.fake.simplicity.utils.creators.RandomCreator
import java.util.*

object DataRepository {

    private var countOfElements = 10
    private val randomizer by lazy {
        Random()
    }
    val items = arrayListOf<Any>()

    fun createRandomData(
        randomCreator: RandomCreator,
        typesArray: List<Class<*>>
    ): List<Any> {
        items.clear()
        val results = arrayListOf<Any>()
        countOfElements =
            getRandomCountOfElements()
        for (i in 0 until countOfElements) {
            val type = typesArray[getRandomIndex(
                typesArray.size - 1
            )]
            randomCreator.createObject(type)?.also { results.add(it) }
        }
        items.addAll(results)
        return results
    }

    private fun getRandomIndex(max: Int): Int = randomizer.nextInt(max + 1)


    private fun getRandomCountOfElements(): Int {
        val diff = MAX_ELEMENTS_COUNT - MIN_ELEMENTS_COUNT
        return randomizer.nextInt(diff + 1) + MIN_ELEMENTS_COUNT
    }

    fun getItem(position: Int): Any {
        return items[position]
    }

}