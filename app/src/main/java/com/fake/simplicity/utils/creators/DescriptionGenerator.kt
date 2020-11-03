package com.fake.simplicity.utils.creators

import com.fake.simplicity.utils.ValueFormatter
import kotlin.reflect.full.declaredMemberProperties

object DescriptionGenerator {
    fun generateDescription(item: Any): String {
        val result = arrayListOf("<h3><b>${item::class.simpleName}</b></h3>")
        item::class.declaredMemberProperties.forEach { member ->
            val text = "<b>${member.name}:</b> ${ValueFormatter.formatValue(member.call(item))}"
            result.add(text)
        }
        return result.joinToString("<br>")
    }
}