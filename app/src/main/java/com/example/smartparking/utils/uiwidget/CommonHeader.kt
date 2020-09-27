package com.example.smartparking.utils.uiwidget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.smartparking.R
import com.example.smartparking.databinding.LayoutCommonHeaderBinding

class CommonHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding = LayoutCommonHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val attr = context.obtainStyledAttributes(
            attrs, R.styleable.CommonHeader, defStyleAttr, 0
        )
        val headerTitle = attr.getString(R.styleable.CommonHeader_headerTitle) ?: ""
        val leftIconRes = attr.getResourceId(R.styleable.CommonHeader_leftIcon, -1)
        val rightIconRes = attr.getResourceId(R.styleable.CommonHeader_rightIcon, -1)
        val noHeaderColor = 987654321
        val bgColor = attr.getColor(R.styleable.CommonHeader_headerBackgroundColor, noHeaderColor)
        val textColor = attr.getColor(R.styleable.CommonHeader_headerTextColor, ContextCompat.getColor(context, R.color.ui_light))
        attr.recycle()
    }
}