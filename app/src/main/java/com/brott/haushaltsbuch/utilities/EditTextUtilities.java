package com.brott.haushaltsbuch.utilities;

import android.widget.EditText;

public class EditTextUtilities {

    /**
     * determines if the passed in EditText is empty
     *
     * @param editText
     * @return true if the EditText is empty or false otherwise
     */
    public static boolean isEmpty(EditText editText) {
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }
}
