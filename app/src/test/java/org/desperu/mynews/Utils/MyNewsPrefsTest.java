package org.desperu.mynews.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyNewsPrefsTest {

    @Mock Context mockContext;
    @Mock SharedPreferences mockPrefs;

    private String key = "test";
    private int intTest = 1;
    private String stringTest = "A string !";
    private Long longTest = System.currentTimeMillis();
    private boolean booleanTest = true;

    @Before
    public void before() throws Exception {
        when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockPrefs);

        when(MyNewsPrefs.getString(mockContext, key, null)).thenReturn(stringTest);
        when(MyNewsPrefs.getInt(mockContext, key, 0)).thenReturn(intTest);
        when(MyNewsPrefs.getLong(mockContext, key, 0)).thenReturn(longTest);
        when(MyNewsPrefs.getBoolean(mockContext, key, false)).thenReturn(booleanTest);
    }

    @Test
    public void Given_String_When_getString_Then_checkValue() {
        String output = MyNewsPrefs.getString(mockContext, key, null);

        assertEquals(stringTest, output);
    }

    @Test
    public void Given_integer_When_getInt_Then_checkValue() {
        int output = MyNewsPrefs.getInt(mockContext, key, 0);

        assertEquals(intTest, output);
    }

    @Test
    public void Given_Long_When_getLong_Then_checkValue() {
        Long output = MyNewsPrefs.getLong(mockContext, key, 0);

        assertEquals(longTest, output);
    }

    @Test
    public void Given_boolean_When_getBoolean_Then_checkValue() {
        boolean output = MyNewsPrefs.getBoolean(mockContext, key, false);

        assertEquals(booleanTest, output);
    }
}
