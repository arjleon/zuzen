package elor.leon.android.zuzen;

import android.text.Editable;
import android.widget.EditText;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import elor.leon.android.zuzen.validators.BasicMinMaxValidator;

import static org.mockito.Mockito.when;

/**
 * Created by arjleon on 6/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidationSetTest {

    private static final String TEXT_1 = "moon";
    private static final String TEXT_2 = "sun";
    private static final String TEXT_3 = "stars";
    private static final int MIN = 4;
    private static final int MAX = 10;
    private static final BasicMinMaxValidator MIN_MAX_VALIDATOR = new BasicMinMaxValidator(MIN, MAX);

    @Mock private EditText mEditText1;
    @Mock private Editable mEditable1;

    @Mock private EditText mEditText2;
    @Mock private Editable mEditable2;

    @Before
    public void setUp() throws Exception {

        when(mEditable1.toString()).thenReturn(TEXT_1);
        when(mEditText1.getText()).thenReturn(mEditable1);

        when(mEditable2.toString()).thenReturn(TEXT_2);
        when(mEditText2.getText()).thenReturn(mEditable2);
    }

    @Test
    public void testBasicSet() {
        Validation<EditText> v1 = new Validation<>(mEditText1, MIN_MAX_VALIDATOR);
        Validation<EditText> v2 = new Validation<>(mEditText2, MIN_MAX_VALIDATOR);

        //chain 2 to 1 (more can be chained before validating)
        ValidationSet set = v1.chain(v2);
        ValidationResult result = set.validate();

        Assert.assertFalse("Result must report invalid.", result.isValid());
        Assert.assertSame("Result must report same View as the failing one.", mEditText2, result.getFormView());

        //override returned string by View originally failing
        when(mEditable2.toString()).thenReturn(TEXT_3);

        result = set.validate();

        Assert.assertTrue("Result must now be valid.", result.isValid());
    }
}
