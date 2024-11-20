package com.kinsidelibs.find_combination.core

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

open class BaseActivity : AppCompatActivity() {
    /**
     * Hide keyboard
     * dismiss keyboard
     * @param view view that making request to hide keyboard
     */
    fun hideKeyboard(view: View) {
        //imm = input method manager
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Resize on keyboard open
     * resize screen scroll view when keyboard appears on screen
     * @param scrollView
     */
    fun resizeOnKeyboardOpen(scrollView: ScrollView) {
        scrollView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            scrollView.getWindowVisibleDisplayFrame(rect) // visible height without keyboard
            val heightDifference = scrollView.rootView.height - rect.bottom
            if (heightDifference > 100) { // Adjust the threshold as needed
                // Keyboard is open
                scrollView.layoutParams.height =
                    scrollView.rootView.height - heightDifference  // you can subtract padding as needed for more Adjustments
                scrollView.requestLayout()
            } else {
                // Keyboard is closed
                scrollView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                scrollView.requestLayout()
            }
        }
    }

    fun disableNightMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}