package com.leaflet.gameapp.presentation.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.models.Level;
import com.leaflet.gameapp.presentation.ui.fragments.PlayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, PlayFragment.OnFragmentInteractionListener {
    @BindView(R.id.navigationView)
    BottomNavigationView navigationView;

    private Level level;
    private MenuItem currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setOnNavigationItemSelectedListener(this);
        level = Level.LEVEL_EASY;
        changePlayFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        currentLevel = menu.findItem(R.id.menu_item_level_selected);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_level_easy:
                applyLevel(Level.LEVEL_EASY);
                break;
            case R.id.menu_item_level_medium:
                applyLevel(Level.LEVEL_MEDIUM);
                break;
            case R.id.menu_item_level_hard:
                applyLevel(Level.LEVEL_HARD);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void applyLevel(Level level) {
        this.level = level;
        switch (level) {
            case LEVEL_EASY:
                currentLevel.setTitle(R.string.menu_action_level_easy);
                break;
            case LEVEL_MEDIUM:
                currentLevel.setTitle(R.string.menu_action_level_medium);
                break;
            case LEVEL_HARD:
                currentLevel.setTitle(R.string.menu_action_level_hard);
                break;
        }
        changePlayFragment();
    }

    private void changePlayFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = PlayFragment.newInstance(level.getId());
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_play:

                break;
            case R.id.navigation_score:

                break;
            case R.id.navigation_exit:

                break;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}