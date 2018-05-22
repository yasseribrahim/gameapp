package com.leaflet.gameapp.presentation.ui.activities;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.communicator.CountDownTimerCallback;
import com.leaflet.gameapp.domain.communicator.OnChangeLevelCallback;
import com.leaflet.gameapp.domain.communicator.OnScoreChanged;
import com.leaflet.gameapp.domain.communicator.OnShowHint;
import com.leaflet.gameapp.domain.models.Level;
import com.leaflet.gameapp.domain.utils.ConstantsValues;
import com.leaflet.gameapp.presentation.ui.fragments.ChangeLevelBottomSheetFragment;
import com.leaflet.gameapp.presentation.ui.fragments.PlayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, PlayFragment.OnFragmentInteractionListener, OnScoreChanged, OnChangeLevelCallback {
    @BindView(R.id.navigationView)
    BottomNavigationView navigationView;

    @BindView(R.id.score)
    TextView scoreView;

    @BindView(R.id.timer)
    TextView timerView;

    @BindView(R.id.progress)
    ProgressBar progress;

    private CustomCountDownTimer timer, hintTimer;
    private Level level;
    private MenuItem currentLevel;
    private int score;
    private OnShowHint onShowHint;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setOnNavigationItemSelectedListener(this);
        level = Level.LEVEL_MEDIUM;

        score = 0;

        handler = new Handler();

        timer = new CustomCountDownTimer(2 * 60 * 1000, 1000, new CountDownTimerCallback() {
            @Override
            public void onTickCallback() {
                int seconds = timer.getCounter() % 60;
                int minutes = timer.getCounter() / 60;

                String secondsAsString = seconds + "";
                String minutesAsString = minutes + "";

                if (seconds <= 9) {
                    secondsAsString = "0" + seconds;
                }
                if (minutes <= 9) {
                    minutesAsString = "0" + minutes;
                }
                timerView.setText(minutesAsString + ":" + secondsAsString);
            }

            @Override
            public void onFinish() {

            }
        });

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
    protected void onDestroy() {
        timer.cancel();
        hintTimer.cancel();
        destroyHandler();
        super.onDestroy();
    }

    private void destroyHandler() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
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

    public void setOnShowHint(OnShowHint onShowHint) {
        this.onShowHint = onShowHint;
    }

    private void applyLevel(Level level) {
        this.level = level;
        switch (level) {
            case LEVEL_EASY:
                currentLevel.setTitle(R.string.action_level_easy);
                break;
            case LEVEL_MEDIUM:
                currentLevel.setTitle(R.string.action_level_medium);
                break;
            case LEVEL_HARD:
                currentLevel.setTitle(R.string.action_level_hard);
                break;
        }
        changePlayFragment();
    }

    private void resetHintTimer() {
        if (hintTimer != null) {
            hintTimer.cancel();
        }

        progress.setProgress(0);
        hintTimer = new CustomCountDownTimer(ConstantsValues.HINT_TIME, 1000, new CountDownTimerCallback() {
            @Override
            public void onTickCallback() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progress.setProgress(hintTimer.getCounter(), true);
                } else {
                    progress.setProgress(hintTimer.getCounter());
                }
            }

            @Override
            public void onFinish() {
                onShowHint.onShowHint();

                destroyHandler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        resetHintTimer();
                    }
                };
                handler.postDelayed(runnable, ConstantsValues.HINT_TIME_DISPLAY);
            }
        });
        hintTimer.start();
    }

    private void changePlayFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = PlayFragment.newInstance(level.getId());
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        clearIndicatorBar();
    }

    private void clearIndicatorBar() {
        scoreView.setText("Score: 0");
        timerView.setText("00:00");
        timer.cancel();
        timer.start();

        resetHintTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_play:

                break;
            case R.id.navigation_score:
                changeLevel();
                break;
            case R.id.navigation_exit:

                break;
        }
        return false;
    }

    private void changeLevel() {
        ChangeLevelBottomSheetFragment dialog = ChangeLevelBottomSheetFragment.newInstance(level.getId());

        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onScoreChange() {
        scoreView.setText("Score: " + (++score));
    }

    @Override
    public void onChangeLevelCallback(Level level) {
        this.level = level;
        changePlayFragment();
    }

    public class CustomCountDownTimer extends CountDownTimer {
        private int counter;
        private CountDownTimerCallback callback;

        public CustomCountDownTimer(long millisInFuture, long countDownInterval, CountDownTimerCallback callback) {
            super(millisInFuture, countDownInterval);
            this.counter = 0;
            this.callback = callback;
        }

        public int getCounter() {
            return counter;
        }

        @Override
        public void onTick(long l) {
            counter++;
            callback.onTickCallback();
        }

        @Override
        public void onFinish() {
            callback.onFinish();
        }
    }
}