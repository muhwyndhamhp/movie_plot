package io.muhwyndhamhp.moviedb.util

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.muhwyndhamhp.moviedb.R

object Extension {

    fun RecyclerView.updateVisibleItem(listener: (Int) -> Unit) {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = this@updateVisibleItem.layoutManager as LinearLayoutManager
                listener.invoke(layoutManager.findFirstCompletelyVisibleItemPosition())
            }
        })
    }

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
                        reason = context.getString(R.string.empty_nickname)
                    }
                    p0.length < 6 || !p0.checkHasUppercase() -> {
                        isValid = false
                        reason = context.getString(R.string.invalid_nickname)
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
                (keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                listener.invoke()
                return@setOnKeyListener false
            }
            false

        }

        this.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                listener.invoke()
                return@setOnEditorActionListener false
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