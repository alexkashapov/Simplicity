package com.fake.simplicity.utils.creators


open class RandomCreator(private var mapTypes: Map<Class<out Any>, () -> Any> = hashMapOf()) {
    fun createObject(type: Class<out Any>): Any?{
        val func = mapTypes[type]
        val res = func?.invoke()
        return res
    }
}