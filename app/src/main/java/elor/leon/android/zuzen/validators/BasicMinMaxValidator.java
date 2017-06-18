package elor.leon.android.zuzen.validators;

import android.widget.EditText;

import elor.leon.android.zuzen.Validator;

/**
 * Created by arjleon on 6/17/2017.
 */

public final class BasicMinMaxValidator implements Validator<EditText> {

    private int mMin;
    private int mMax;
    private boolean mTrim;

    public BasicMinMaxValidator(int min, int max) {
        this(min, max, false);
    }

    public BasicMinMaxValidator(int min, int max, boolean trim) {

        if (min < 0) {
            throw new IllegalArgumentException("Minimum value cannot be negative.");
        }

        if (max < 0) {
            throw new IllegalArgumentException("Maximum value cannot be negative.");
        }

        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than the maximum value.");
        }

        mMin = min;
        mMax = max;
        mTrim = trim;
    }

    @Override
    public boolean isValid(EditText input) {

        if (input == null || input.getText() == null) {
            return false;
        }

        String string = input.getText().toString();

        if (mTrim) {
            string = string.trim();
        }

        final int len = string.length();

        return len >= mMin && len <= mMax;
    }
}
