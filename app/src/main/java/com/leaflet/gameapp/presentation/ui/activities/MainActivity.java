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
import com.leaflet.gameapp.domain.listeners.OnDimensionChanged;
import com.leaflet.gameapp.domain.models.Dimension;
import com.leaflet.gameapp.presentation.ui.fragments.PlayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, PlayFragment.OnFragmentInteractionListener {
    @BindView(R.id.navigationView)
    BottomNavigationView navigationView;

    private OnDimensionChanged onDimensionChanged;
    private Dimension dimension;
    private MenuItem currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setOnNavigationItemSelectedListener(this);
        dimension = Dimension.DIMENSION_2_BY_3;
        addPlayFragment();
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
                applyDimension(Dimension.DIMENSION_2_BY_3);
                break;
            case R.id.menu_item_level_medium:
                applyDimension(Dimension.DIMENSION_3_BY_4);
                break;
            case R.id.menu_item_level_hard:
                applyDimension(Dimension.DIMENSION_4_BY_5);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void applyDimension(Dimension dimension) {
        this.dimension = dimension;
        switch (dimension) {
            case DIMENSION_2_BY_3:
                currentLevel.setTitle(R.string.menu_action_level_easy);
                break;
            case DIMENSION_3_BY_4:
                currentLevel.setTitle(R.string.menu_action_level_medium);
                break;
            case DIMENSION_4_BY_5:
                currentLevel.setTitle(R.string.menu_action_level_hard);
                break;

        }
        onDimensionChanged.onDimensionChanges(dimension.getDimensionX(), dimension.getDimensionY());
    }

    private void addPlayFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = PlayFragment.newInstance(dimension.getDimensionX(), dimension.getDimensionY());
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

    public void setOnDimensionChanged(OnDimensionChanged onDimensionChanged) {
        this.onDimensionChanged = onDimensionChanged;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}