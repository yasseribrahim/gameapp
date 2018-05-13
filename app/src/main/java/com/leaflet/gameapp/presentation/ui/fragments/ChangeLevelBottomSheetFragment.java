package com.leaflet.gameapp.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.communicator.OnChangeLevelCallback;
import com.leaflet.gameapp.domain.models.Level;
import com.leaflet.gameapp.domain.utils.ConstantsIds;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yasser.ibrahim on 5/13/2018.
 */

public class ChangeLevelBottomSheetFragment extends BottomSheetDialogFragment {

    private int levelId;
    private OnChangeLevelCallback callback;

    public static ChangeLevelBottomSheetFragment newInstance(int levelId) {
        ChangeLevelBottomSheetFragment fragment = new ChangeLevelBottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsIds.LEVEL_ID, levelId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        ButterKnife.bind(this, view);

        levelId = getArguments().getInt(ConstantsIds.LEVEL_ID);
        Level level = Level.parse(levelId);

        return view;
    }

    private void onClick(RadioButton box) {
        switch (box.getId()) {
            case R.id.level_easy:
                callback.onChangeLevelCallback(Level.LEVEL_EASY);
                break;
            case R.id.level_medium:
                callback.onChangeLevelCallback(Level.LEVEL_MEDIUM);
                break;
            case R.id.level_hard:
                callback.onChangeLevelCallback(Level.LEVEL_HARD);
                break;
        }

        dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnChangeLevelCallback) context;
    }

    @OnClick(R.id.level_easy)
    public void onLevelEasyClick(RadioButton box) {
        onClick(box);
    }

    @OnClick(R.id.level_medium)
    public void onLevelMediumClick(RadioButton box) {
        onClick(box);
    }

    @OnClick(R.id.level_hard)
    public void onLevelHardClick(RadioButton box) {
        onClick(box);
    }
}
