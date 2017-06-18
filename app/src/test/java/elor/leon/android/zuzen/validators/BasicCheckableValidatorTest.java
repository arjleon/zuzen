package elor.leon.android.zuzen.validators;

import android.widget.CompoundButton;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * Created by arjleon on 6/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicCheckableValidatorTest {

    @Mock
    private CompoundButton mCompoundButton;

    @Test
    public void testCheckedDesired() throws Exception {
        when(mCompoundButton.isChecked()).thenReturn(true);
        boolean isValid = new BasicCheckableValidator(true).isValid(mCompoundButton);
        Assert.assertTrue("The compound button should have the desired state.", isValid);
    }

    @Test
    public void testCheckedNotDesired() throws Exception {
        when(mCompoundButton.isChecked()).thenReturn(true);
        boolean isValid = new BasicCheckableValidator(false).isValid(mCompoundButton);
        Assert.assertFalse("The compound button should have the undesired state.", isValid);
    }

    @Test
    public void testUncheckedDesired() throws Exception {
        when(mCompoundButton.isChecked()).thenReturn(false);
        boolean isValid = new BasicCheckableValidator(false).isValid(mCompoundButton);
        Assert.assertTrue("The compound button should have the desired state.", isValid);
    }

    @Test
    public void testUncheckedNotDesired() throws Exception {
        when(mCompoundButton.isChecked()).thenReturn(false);
        boolean isValid = new BasicCheckableValidator(true).isValid(mCompoundButton);
        Assert.assertFalse("The compound button should have the undesired state.", isValid);
    }
}
