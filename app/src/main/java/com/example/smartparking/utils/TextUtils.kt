package com.example.smartparking.utils

import android.text.Spanned

object TextUtils {
    @JvmStatic
    fun isTextDifferent(str1: CharSequence?, str2: CharSequence?): Boolean {
        if (str1 == str2) {
            return false;
        }
        if ((str1 == null) || (str2 == null)) {
            return true;
        }
        if (str1.length != str2.length) {
            return true;
        }

        if (str1 is Spanned) {
            return str1 != str2
        }

        str1.forEachIndexed { index, c ->
            if (c != str2[index]) {
                return true
            }
        }

        return false;
    }
}
