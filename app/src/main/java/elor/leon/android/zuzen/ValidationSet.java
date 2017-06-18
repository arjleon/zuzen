package elor.leon.android.zuzen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjleon on 6/17/2017.
 */

public final class ValidationSet {

    private List<Validation> mValidations = new ArrayList<>();

    ValidationSet(Validation... validations) {
        for (Validation validation : validations) {
            if (validation != null) {
                mValidations.add(validation);
            }
        }
    }

    public ValidationSet chain(Validation validation) {
        mValidations.add(validation);
        return this;
    }

    public ValidationResult validate() {

        if (mValidations == null || mValidations.isEmpty()) {
            throw new IllegalStateException("There are no Validation objects to validate against.");
        }

        //default value
        ValidationResult result = new ValidationResult(false, null, null);

        for (Validation validation : mValidations) {
            result = validation.validate();

            if (!result.isValid()) {
                break;
            }
        }

        return result;
    }
}
