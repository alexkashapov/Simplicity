package com.fake.simplicity.utils.creators

import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.view.Gravity
import android.widget.TextView
import com.fake.simplicity.R
import com.fake.simplicity.utils.ValueFormatter
import kotlin.reflect.full.declaredMemberProperties

object ViewCreator {
    fun createViews(context: Context, item: Any): List<TextView> {
        val result = arrayListOf<TextView>()
        val titleView = TextView(context)
        val title = item::class.simpleName
        titleView.text = Editable.Factory.getInstance().newEditable("$title")
        titleView.gravity = Gravity.CENTER_HORIZONTAL
        titleView.typeface = Typeface.DEFAULT_BOLD
        result.add(titleView)
        item::class.declaredMemberProperties.forEach { member ->
            val labelView = TextView(
                context, null,
                R.style.TextSecondary
            )
            labelView.text = Editable.Factory.getInstance().newEditable(member.name)
            result.add(labelView)
            val valueView = TextView(context)
            val value = member.call(item)
            valueView.text = Editable.Factory.getInstance().newEditable(
                ValueFormatter.formatValue(value)
            )
            result.add(valueView)
        }
        return result
    }
}