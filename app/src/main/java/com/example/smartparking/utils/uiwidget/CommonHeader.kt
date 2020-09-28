package com.example.smartparking.utils.uiwidget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.smartparking.R
import com.example.smartparking.databinding.LayoutCommonHeaderBinding
import com.example.smartparking.utils.extensions.setBackgroundDrawableAndColor

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

        val hasBottomBorder = attr.getBoolean(R.styleable.CommonHeader_hasBottomBorder, false)
        val hasRoundCorner = attr.getBoolean(R.styleable.CommonHeader_hasRoundCorner, false)

        attr.recycle()

        if (bgColor != noHeaderColor) {
            if (hasRoundCorner) {
                setBackgroundDrawableAndColor(
                    getDrawable(context, R.drawable.bg_bottom_sheet_white), bgColor
                )
            } else {
                setBackgroundColor(bgColor)
            }
        } else {
            setBackgroundDrawableAndColor(getDrawable(context, R.drawable.bg_header_default))
        }

        binding.apply {
            tvTitle.apply {
                text = headerTitle
                setTextColor(textColor)
            }
            setLeftButtonIcon(leftIconRes)
            setRightButtonIcon(rightIconRes)
            bottomBorder.isVisible = hasBottomBorder
        }
    }

    fun setLeftButtonIcon(iconRes: Int) {
        val leftIcon = if (iconRes != -1) getDrawable(context, iconRes) else null
        if (leftIcon != null) {
            binding.icLeftIcon.setImageDrawable(leftIcon)
            binding.icLeftIcon.isVisible = true
        } else {
            binding.icLeftIcon.isVisible = false
        }
    }

    fun setLeftButtonClickListener(onClickListener: OnClickListener) {
        if (binding.icLeftIcon.isVisible) {
            binding.icLeftIcon.setOnClickListener(onClickListener)
        }
    }

    fun setRightButtonIcon(iconRes: Int) {
        val rightIcon = if (iconRes != -1) getDrawable(context, iconRes) else null
        if (rightIcon != null) {
            binding.icRightIcon.setImageDrawable(rightIcon)
            binding.icRightIcon.isVisible = true
        } else {
            binding.icRightIcon.isVisible = false
        }
    }

    fun setRightButtonClickListener(onClickListener: OnClickListener) {
        if (binding.icRightIcon.isVisible) {
            binding.icRightIcon.setOnClickListener(onClickListener)
        }
    }

    fun setHeaderTitle(title: String?) {
        title?.let {
            binding.tvTitle.text = it
        }
    }
}