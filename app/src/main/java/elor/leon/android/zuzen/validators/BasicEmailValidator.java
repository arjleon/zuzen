package elor.leon.android.zuzen.validators;

import android.widget.EditText;

import java.util.regex.Pattern;

import elor.leon.android.zuzen.Validator;

/**
 * Created by arjleon on 6/17/2017.
 */

public final class BasicEmailValidator implements Validator<EditText> {

    /**
     * As of June 16, 2017:
     *
     * Source: http://www.regular-expressions.info/email.html
     *
     * Pretty flexible RegEx. For a more
     * technically correct regular expression
     * check emailregex.com that refers to
     * the official standard (RFC 5322).
     *
     * Links:
     * - emailregex.com
     * - http://www.ietf.org/rfc/rfc5322.txt
     */
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Override
    public boolean isValid(EditText input) {

        if (input == null || input.getText() == null) {
            return false;
        }

        return Pattern.compile(REGEX_EMAIL).matcher(input.getText().toString()).matches();
    }
}
