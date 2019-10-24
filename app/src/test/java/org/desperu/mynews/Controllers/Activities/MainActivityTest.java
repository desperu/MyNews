package org.desperu.mynews.Controllers.Activities;

import android.content.Context;
import android.view.Menu;
//import androidx.test.rule.ActivityTestRule;
import java.lang.reflect.Method;

import org.desperu.mynews.R;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

//@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


//    @Rule
//    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

//    @Test
//    public void Given_When_Then() {
//        MainActivity mainActivity = mock(MainActivity.class);
//        Menu menu = mock(Menu.class);
//        mainActivity.getMenuInflater().inflate(R.menu.activity_main_menu, menu);
//        mainActivity.onCreateOptionsMenu(menu);
//    }

    @Test
    public void Given_configureViewPagerAndTabs_When_Then() {
        MainActivity mainActivity = mock(MainActivity.class);
        // With reflection
        try{

            // Alternatively: Object foo = Foo.class.newInstance();
            Object foo = Class.forName(".Controllers.Activities.MainActivity").newInstance();

            Method m = foo.getClass().getDeclaredMethod("configureViewPagerAndTabs", new Class<?>[0]);
            m.invoke(foo);
//            m.getDefaultValue().equals(R.layout.activity_main);

        }catch(Exception e){

            //Catching ClassNotFoundException, NoSuchMethodException
            //InstantiationException, IllegalAccessException

        }
    }

    @Test
    public void Given_startActivity_When_newInstance_Then_checkActivityLayout() {
        MainActivity mainActivity = new MainActivity();
        int output = mainActivity.getActivityLayout();

        int expected = R.layout.activity_main;
        assertEquals(expected, output);
    }
}
