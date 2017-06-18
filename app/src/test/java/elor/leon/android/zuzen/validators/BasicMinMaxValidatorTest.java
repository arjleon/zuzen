package elor.leon.android.zuzen.validators;

import android.text.Editable;
import android.widget.EditText;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * Created by arjleon on 6/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicMinMaxValidatorTest {

    private static final int MIN = 3;
    private static final int MAX = 14;

    private static final String[] STRINGS_VALID = new String[] {
            "dunellen",
            "wrigglework",
            "madrigal",
            "septuagenarian",
            "blacken",
            "nonstabile",
            "perissodactyle",
            "chon",
            "magically",
            "sketch"
    };

    private static final String[] STRINGS_INVALID = new String[] {
            "",
            "  ",
            "as",
            "go",
            "electrification",
            "reinterrogating",
            "interconsonantal",
            "nonincandescent",
            "ecclesiologically",
            "dj",
    };

    private static final BasicMinMaxValidator BASIC_MIN_MAX_VALIDATOR = new BasicMinMaxValidator(MIN, MAX);

    @Mock
    private EditText mEditText;
    private int mIndex = 0;

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMin() {
        new BasicMinMaxValidator(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMax() {
        new BasicMinMaxValidator(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinGreaterThanMax() {
        new BasicMinMaxValidator(3, 2);
    }

    @Test
    public void testSameMinMaxTrimmedString() {
        final String string = " ZUZEN ";
        Editable mockEditable = Mockito.mock(Editable.class);
        when(mockEditable.toString()).thenReturn(string);
        when(mEditText.getText()).thenReturn(mockEditable);

        final int min = string.trim().length();
        final int max = string.trim().length();

        BasicMinMaxValidator fiveLengthValidator = new BasicMinMaxValidator(min, max, true);
        Assert.assertTrue(string + " must match min and max.", fiveLengthValidator.isValid(mEditText));
    }

    @Test
    public void testAllValidStrings() {
        Editable mockEditable = Mockito.mock(Editable.class);
        when(mockEditable.toString()).thenReturn(STRINGS_VALID[mIndex]);
        when(mEditText.getText()).thenReturn(mockEditable);

        for (int i = 0; i < STRINGS_VALID.length; i++) {
            mIndex = i;
            final String message = "String at " + i + " must be considered valid.";
            Assert.assertTrue(message, BASIC_MIN_MAX_VALIDATOR.isValid(mEditText));
        }
    }

    @Test
    public void testAllInvalidStrings() {
        Editable mockEditable = Mockito.mock(Editable.class);
        when(mockEditable.toString()).thenReturn(STRINGS_INVALID[mIndex]);
        when(mEditText.getText()).thenReturn(mockEditable);

        for (int i = 0; i < STRINGS_INVALID.length; i++) {
            mIndex = i;
            final String message = "String at " + i + " must be considered invalid.";
            Assert.assertFalse(message, BASIC_MIN_MAX_VALIDATOR.isValid(mEditText));
        }
    }

    @Test
    public void testNullValidator() {
        Assert.assertFalse("Null validator should result in invalid.", BASIC_MIN_MAX_VALIDATOR.isValid(null));
    }
}
