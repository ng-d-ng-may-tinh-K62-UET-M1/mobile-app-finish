package com.example.smartparking.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import com.example.smartparking.R
import com.example.smartparking.databinding.LayoutEditTextLineBinding
import com.example.smartparking.utils.TextUtils
import com.example.smartparking.utils.alias.BooleanCallback
import com.example.smartparking.utils.alias.CommonCallback
import com.example.smartparking.utils.alias.StringCallback
import com.example.smartparking.utils.extensions.setMaxLength
import com.example.smartparking.utils.extensions.setOnRightIconClicked
import com.example.smartparking.utils.extensions.setRightIcon
import com.example.smartparking.utils.extensions.showKeyboard

class EditTextLine
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding = LayoutEditTextLineBinding.inflate(LayoutInflater.from(context), this, true)
    private var text: String = ""
    private var callbackOnTextChanged: StringCallback? = null
    private var callbackOnFocusChanged: BooleanCallback? = null
    private var callbackOnClicked: CommonCallback? = null
    private var callbackOnRightIconClicked: CommonCallback? = null

    init {
        val attr = context.obtainStyledAttributes(
            attrs,
            R.styleable.EditTextLine, defStyleAttr, 0
        )

        val label = attr.getString(R.styleable.EditTextLine_label) ?: ""
        val enabled = attr.getBoolean(R.styleable.EditTextLine_enabled, true)
        val maxLength = attr.getInt(R.styleable.EditTextLine_maxLength, -1)
        val showForwardButton = attr.getBoolean(R.styleable.EditTextLine_showForwardButton, false)
        attr.recycle()

        binding.apply {
            textInputLayout.hint = label
            btnForward.visibility = if (showForwardButton) View.VISIBLE else View.GONE

            textInputEditText.apply {
                isFocusable = enabled
                if (maxLength > 0) setMaxLength(maxLength)

                doAfterTextChanged {
                    val newText = text.toString()
                    if (this@EditTextLine.text != newText) {
                        this@EditTextLine.text = newText
                        callbackOnTextChanged?.invoke(newText)
                    }
                }

                setOnFocusChangeListener { _, hasFocus ->
                    callbackOnFocusChanged?.invoke(hasFocus)
                }
                setOnClickListener {
                    callbackOnClicked?.invoke()
                }
                setOnRightIconClicked(OnClickListener {
                    callbackOnRightIconClicked?.invoke()
                })
            }
        }
    }

    fun setDisable(isDisable: Boolean) {
        binding.textInputLayout.isEnabled = !isDisable
    }

    fun focus() {
        binding.textInputEditText.showKeyboard()
    }

    fun setHint(label: String) {
        binding.textInputEditText.hint = label
    }

    fun setInputType(inputType: Int) {
        binding.textInputEditText.inputType = inputType
    }

    fun setImeOption(imeOption: Int) {
        binding.textInputEditText.imeOptions = imeOption
    }

    fun setTextAndCursor(value: CharSequence?) {
        binding.textInputEditText.apply {
            if (TextUtils.isTextDifferent(value, text)) {
                setText(value)
                setSelection(value?.length ?: 0)
            }
        }
    }

    fun setTextColor(@ColorInt color: Int) {
        binding.textInputEditText.setTextColor(color)
    }

    fun setTextChangeListener(callback: (String) -> Unit) {
        callbackOnTextChanged = callback
    }

    fun setOnFocusChanged(callback: BooleanCallback) {
        callbackOnFocusChanged = callback
    }

    fun setOnClickEditText(callback: CommonCallback) {
        callbackOnClicked = callback
    }

    fun setOnRightIconClicked(callback: CommonCallback) {
        callbackOnRightIconClicked = callback
    }

    fun setRightIcon(icon: Drawable?) {
        if (icon != null) binding.textInputEditText.setRightIcon(icon)
    }

}