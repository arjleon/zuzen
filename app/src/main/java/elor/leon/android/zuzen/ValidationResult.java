package elor.leon.android.zuzen;

/**
 * Created by arjleon on 6/17/2017.
 */

public final class ValidationResult <T> {

    private T mFormView;
    private Validator<T> mValidator;
    private boolean mIsValid;

    ValidationResult(boolean isValid, T formView, Validator<T> validator) {
        mIsValid = isValid;
        mFormView = formView;
        mValidator = validator;
    }

    public T getFormView() {
        return mFormView;
    }

    public Validator<T> getValidator() {
        return mValidator;
    }

    public boolean isValid() {
        return mIsValid;
    }
}
