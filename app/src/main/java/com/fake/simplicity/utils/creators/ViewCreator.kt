package com.fake.simplicity.utils.creators

import android.content.Context
import android.text.Editable
import android.widget.TextView
import com.fake.simplicity.R
import com.fake.simplicity.utils.toText
import com.fake.simplicity.utils.toTimeText
import java.util.*
import kotlin.reflect.full.declaredMemberProperties

object ViewCreator {
    fun createViews(context: Context, item: Any): List<TextView>{
        val result = arrayListOf<TextView>()
        item::class.declaredMemberProperties.forEach { member ->
            val labelView = TextView(context, null,
                R.style.TextSecondary
            )
            labelView.text = Editable.Factory.getInstance().newEditable(member.name)
            result.add(labelView)
            val valueView = TextView(context)
            val value = member.call(item)
            valueView.text = Editable.Factory.getInstance().newEditable(
                when (value) {
                    is Date -> value.toText()
                    member.name.toLowerCase()
                        .contains("time") && value is Double -> (value as Double).toTimeText()
                    else -> value.toString()
                }
            )
            result.add(valueView)
        }
        return result
    }
}