package elor.leon.android.zuzen.validators;

import android.text.Editable;
import android.widget.EditText;

import org.junit.Assert;
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
public class BasicEmailValidatorTest {

    private static final String[] EMAILS_VALID = new String[] {
            "email@example.com",
            "firstname.lastname@example.com",
            "email@subdomain.example.com",
            "firstname+lastname@example.com",
            "email@123.123.123.123",
            "email@[123.123.123.123]",
            "“email”@example.com",
            "1234567890@example.com",
            "email@example-one.com",
            "_______@example.com",
            "email@example.name",
            "email@example.museum",
            "email@example.co.jp",
            "firstname-lastname@example.com"
    };

    private static final String[] EMAILS_INVALID = new String[] {
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@example.com",
            "Joe Smith <email@example.com>",
            "email.example.com",
            "email@example@example.com",
            ".email@example.com",
            "email.@example.com",
            "email..email@example.com",
            "あいうえお@example.com",
            "email@example.com (Joe Smith)",
            "email@example",
            "email@-example.com",
            "email@example.web",
            "email@111.222.333.44444",
            "email@example..com",
            "Abc..123@example.com"
    };

    private static final BasicEmailValidator BASIC_EMAIL_VALIDATOR = new BasicEmailValidator();

    @Mock
    private EditText mEditText;
    private int mIndex = 0;

    @Test
    public void testAllValidEmails() throws Exception {
        Editable mockEditable = Mockito.mock(Editable.class);
        when(mockEditable.toString()).thenReturn(EMAILS_VALID[mIndex]);
        when(mEditText.getText()).thenReturn(mockEditable);

        for (int i = 0; i < EMAILS_VALID.length; i++) {
            mIndex = i;
            final String message = "Email at " + i + " must be considered valid.";
            Assert.assertTrue(message, BASIC_EMAIL_VALIDATOR.isValid(mEditText));
        }
    }

    @Test
    public void testAllInvalidEmails() throws Exception {
        Editable mockEditable = Mockito.mock(Editable.class);
        when(mockEditable.toString()).thenReturn(EMAILS_INVALID[mIndex]);
        when(mEditText.getText()).thenReturn(mockEditable);

        for (int i = 0; i < EMAILS_VALID.length; i++) {
            mIndex = i;
            final String message = "Email at " + i + " must be considered invalid.";
            Assert.assertFalse(message, BASIC_EMAIL_VALIDATOR.isValid(mEditText));
        }
    }
}
