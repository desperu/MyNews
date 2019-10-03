package org.desperu.mynews;

import android.view.Menu;
//import androidx.test.rule.ActivityTestRule;
import org.desperu.mynews.Controllers.Activities.MainActivity;
import java.lang.reflect.Method;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

//@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
//    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

//    @Test
//    public void Given_When_Then() {
//        MainActivity mainActivity = mock(MainActivity.class);
//        Menu menu = mock(Menu.class);
//        mainActivity.getMenuInflater().inflate(R.menu.menu_activity_main, menu);
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

        }catch(Exception e){

            //Catching ClassNotFoundException, NoSuchMethodException
            //InstantiationException, IllegalAccessException

        }
    }
}
