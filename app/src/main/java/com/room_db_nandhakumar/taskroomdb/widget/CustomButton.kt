package com.room_db_nandhakumar.taskroomdb.widget

import android.content.Context
import android.util.AttributeSet

class CustomButton :androidx.appcompat.widget.AppCompatButton {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    fun init() {
        // val face = Typeface.createFromAsset(context.assets, "fonts/Lato_Regular.ttf")
        // this.typeface = face
    }
}