package com.fake.simplicity.utils.creators

import kotlin.reflect.full.declaredMemberProperties

object DescriptionGenerator {
    fun generateDescription(item: Any): String {
        val result = arrayListOf("${item::class.simpleName}")
        item::class.declaredMemberProperties.forEach { member ->
            val text = "<b>${member.name}</b> ${member.call(item)}"
            result.add(text)
        }
        return result.joinToString("<br>")
    }
}