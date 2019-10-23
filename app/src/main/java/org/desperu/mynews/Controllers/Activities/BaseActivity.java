package org.desperu.mynews.Controllers.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.desperu.mynews.R;

import butterknife.ButterKnife;
import icepick.Icepick;

public abstract class BaseActivity extends AppCompatActivity {

    // --------------
    // BASE METHODS
    // --------------

    protected abstract int getActivityLayout();
    protected abstract void configureDesign();
    protected abstract void updateDesign();

    // -----------------
    // METHODS OVERRIDE
    // -----------------

    // TODO check if need icepick
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        ButterKnife.bind(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
        this.configureDesign();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateDesign();
    }

    // TODO useless
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    // --------------
    // DESIGN
    // --------------

    protected void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //TODO restart activity
    protected void configureUpButton(){
        ActionBar ab = getSupportActionBar();
        if (ab != null) ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Respond to the action bar's Up/Home button
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
