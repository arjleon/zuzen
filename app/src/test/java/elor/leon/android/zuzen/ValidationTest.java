package elor.leon.android.zuzen;

import android.text.Editable;
import android.widget.EditText;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import elor.leon.android.zuzen.validators.BasicMinMaxValidator;

import static org.mockito.Mockito.when;

/**
 * Created by arjleon on 6/17/2017.
 */
public class ValidationTest {

    @Test
    public void testValidation() {

        final EditText editText = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);

        when(editable.toString()).thenReturn("zuzen");
        when(editText.getText()).thenReturn(editable);

        final int viewId = 123;
        when(editText.getId()).thenReturn(viewId);

        BasicMinMaxValidator minMaxValidator = new BasicMinMaxValidator(4, 6);
        Validation<EditText> validation = new Validation<>(editText, minMaxValidator);
        ValidationResult<EditText> result = validation.validate();

        Assert.assertFalse("Result must not be null.", result == null);
        Assert.assertSame("Result must return same View instance.", result.getFormView(), editText);
        Assert.assertTrue("Result must return same View instance.", result.getFormView().getId() == editText.getId());
        Assert.assertTrue("Returned validator must be the same instance originally passed.", result.getValidator() == minMaxValidator);
        Assert.assertTrue("Result must be of a valid input.", result.isValid());
    }
}
