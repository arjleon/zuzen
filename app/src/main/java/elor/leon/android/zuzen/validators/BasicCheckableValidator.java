package elor.leon.android.zuzen.validators;

import android.widget.CompoundButton;

import elor.leon.android.zuzen.Validator;

/**
 * Created by arjleon on 6/17/2017.
 */

public final class BasicCheckableValidator implements Validator<CompoundButton> {

    private boolean mShouldBeChecked;

    public BasicCheckableValidator() {
        this(true);
    }

    public BasicCheckableValidator(boolean shouldBeChecked) {
        mShouldBeChecked = shouldBeChecked;
    }

    @Override
    public boolean isValid(CompoundButton input) {
        return mShouldBeChecked == input.isChecked();
    }
}
