package com.ryvk.androidcustomcomponents

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.annotation.ColorInt

class ControllerButton : FrameLayout {

    private lateinit var buttonUp: ImageButton
    private lateinit var buttonDown: ImageButton
    private lateinit var buttonLeft: ImageButton
    private lateinit var buttonRight: ImageButton
    private lateinit var middleButton: Button

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.controller_button, this, true)

        buttonUp = findViewById(R.id.imageButtonUp)
        buttonDown = findViewById(R.id.imageButtonDown)
        buttonLeft = findViewById(R.id.imageButtonLeft)
        buttonRight = findViewById(R.id.imageButtonRight)
        middleButton = findViewById(R.id.middleButton)
    }

    fun setBackgroundTint(button: Type, @ColorInt color: Int) {
        val selectedButton = resolveButton(button)
        selectedButton.backgroundTintList = ColorStateList.valueOf(color)
    }

    fun setIconTint(button: Type, @ColorInt color: Int) {
        if (button == Type.BUTTON_MIDDLE) {
            // Middle button is a regular Button, doesn't have setColorFilter
            return
        }
        val selectedButton = resolveButton(button) as? ImageButton
        selectedButton?.setColorFilter(color)
    }

    fun setMiddleButtonTint(@ColorInt color: Int) {
        middleButton.backgroundTintList = ColorStateList.valueOf(color)
    }

    fun setOnClickListener(button: Type, listener: OnClickListener?) {
        val selectedButton = resolveButton(button)
        selectedButton.setOnClickListener(listener)
    }

    private fun resolveButton(button: Type) = when(button) {
        Type.BUTTON_UP -> buttonUp
        Type.BUTTON_DOWN -> buttonDown
        Type.BUTTON_LEFT -> buttonLeft
        Type.BUTTON_RIGHT -> buttonRight
        Type.BUTTON_MIDDLE -> middleButton
    }

    enum class Type {
        BUTTON_UP, BUTTON_DOWN, BUTTON_LEFT, BUTTON_RIGHT, BUTTON_MIDDLE
    }
}