package elor.leon.android.zuzen;

/**
 * Created by arjleon on 6/17/2017.
 */

public final class Validation <T> {

    private T mFormView;
    private Validator<T> mValidator;

    public Validation(T formView, Validator<T> validator) {
        mFormView = formView;
        mValidator = validator;
    }

    public ValidationSet chain(Validation validation) {
        return new ValidationSet(this, validation);
    }

    public ValidationResult validate() {
        final boolean isValid = mValidator.isValid(mFormView);
        return new ValidationResult<T>(isValid, mFormView, mValidator);
    }
}
