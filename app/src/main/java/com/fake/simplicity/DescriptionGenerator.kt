package com.fake.simplicity

import kotlin.reflect.full.declaredMemberProperties

object DescriptionGenerator {
    fun generateDescription(item: Any): String {
        val result = arrayListOf<String>()
        item::class.declaredMemberProperties.forEach { member ->
            val text = "<b>${member.name}</b> ${member.call(item)}"
            result.add(text)
        }
        return result.joinToString("<br>")
    }
}