package org.desperu.mynews.Controllers.Activities;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import org.desperu.mynews.R;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BaseActivityTest {

    @Test
    public void checkOnCreate() {
        BaseActivity baseActivity = mock(BaseActivity.class);
        baseActivity.onCreate(Bundle.EMPTY);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        baseActivity.getBaseContext();
        baseActivity.configureToolbar();
//        Toolbar toolbar = baseActivity.configureToolbar();
        baseActivity.getViewModelStore();
        int output = baseActivity.getActivityLayout();

        int expected = 0;
        assertEquals(expected, output);
    }
}
