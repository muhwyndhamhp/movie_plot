package io.muhwyndhamhp.moviedb.util

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText

object Extension {

    fun EditText.assertNickName(assert: (isValid: Boolean, reason: String?) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                var isValid = true
                var reason = ""

                when {
                    p0.isNullOrEmpty() -> {
                        isValid = false
                        reason = "Nickname cannot be empty!"
                    }
                    p0.length < 6 || !p0.checkHasUppercase() -> {
                        isValid = false
                        reason = "Your nickname must at least:\n - 6 characters\n - 1 capital letter"
                    }
                }

                assert.invoke(isValid, reason)
            }

        })
    }

    fun EditText.onReturnKey(listener: () -> Unit) {
        this.setOnKeyListener { _, keyCode, keyEvent ->
            // If the event is a key-down event on the "enter" button
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Perform action on key press
                listener.invoke()
                return@setOnKeyListener true
            }
            false

        }
    }

    fun CharSequence.checkHasUppercase(): Boolean {
        var ch: Char
        var capitalFlag = false
        for (element in this) {
            ch = element
            if (Character.isUpperCase(ch)) {
                capitalFlag = true
            }
            if (capitalFlag) return true
        }
        return false
    }
}