package elor.leon.android.zuzen;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ValidatorTest {

    @Test
    public void testInvalidEmptyStringValidator1() throws Exception {

        boolean result = new NonEmptyStringValidator().isValid(null);
        Assert.assertFalse("String must be invalid since it is null", result);
    }

    @Test
    public void testInvalidEmptyStringValidator2() throws Exception {

        boolean result = new NonEmptyStringValidator().isValid("");
        Assert.assertFalse("String must be invalid since it is empty", result);
    }

    @Test
    public void testInvalidEmptyStringValidator3() throws Exception {

        boolean result = new NonEmptyStringValidator().isValid("   ");
        Assert.assertFalse("String must be invalid since it is empty", result);
    }

    @Test
    public void testInvalidEmptyStringValidator4() throws Exception {

        boolean result = new NonEmptyStringValidator().isValid("  \n ");
        Assert.assertFalse("String must be invalid since it is empty", result);
    }

    @Test
    public void testValidStringValidator1() throws Exception {

        boolean result = new NonEmptyStringValidator().isValid("test1");
        Assert.assertTrue("String must be valid since it is not empty", result);
    }

    @Test
    public void testValidStringValidator2() throws Exception {

        boolean result = new NonEmptyStringValidator().isValid(" test2 ");
        Assert.assertTrue("String must be valid since it is not empty", result);
    }

    private class NonEmptyStringValidator implements Validator<String> {

        @Override
        public boolean isValid(String input) {
            return input != null && !input.trim().isEmpty();
        }
    }
}