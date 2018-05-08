package com.leaflet.gameapp.presentation.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.communicator.OnScoreChange;
import com.leaflet.gameapp.domain.models.Level;
import com.leaflet.gameapp.domain.utils.ConstantsIds;
import com.leaflet.gameapp.presentation.ui.util.ItemsClickedManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class PlayFragment extends Fragment implements OnScoreChange {

    private OnFragmentInteractionListener listener;
    private int levelId;
    private Level level;
    private ItemsClickedManager manager;
    private OnScoreChange onScoreChange;

    public PlayFragment() {
    }

    public static PlayFragment newInstance(int levelId) {
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putInt(ConstantsIds.LEVEL_ID, levelId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            levelId = getArguments().getInt(ConstantsIds.LEVEL_ID);
            level = Level.parse(levelId);
            manager = new ItemsClickedManager(getContext(), level, this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(level.getResource(), container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onScoreChange = (OnScoreChange) context;
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onScoreChange() {
        onScoreChange.onScoreChange();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void onItemClicked(ImageView image, int row, int column) {
        manager.onClick(image, row, column);
    }

    @Optional
    @OnClick(R.id.item_1_1)
    public void onItem11Clicked(View view) {
        onItemClicked((ImageView) view, 1, 1);
    }

    @Optional
    @OnClick(R.id.item_1_2)
    public void onItem12Clicked(View view) {
        onItemClicked((ImageView) view, 1, 2);
    }

    @Optional
    @OnClick(R.id.item_1_3)
    public void onItem13Clicked(View view) {
        onItemClicked((ImageView) view, 1, 3);
    }

    @Optional
    @OnClick(R.id.item_1_4)
    public void onItem14Clicked(View view) {
        onItemClicked((ImageView) view, 1, 4);
    }

    @Optional
    @OnClick(R.id.item_2_1)
    public void onItem21Clicked(View view) {
        onItemClicked((ImageView) view, 2, 1);
    }

    @Optional
    @OnClick(R.id.item_2_2)
    public void onItem22Clicked(View view) {
        onItemClicked((ImageView) view, 2, 2);
    }

    @Optional
    @OnClick(R.id.item_2_3)
    public void onItem23Clicked(View view) {
        onItemClicked((ImageView) view, 2, 3);
    }

    @Optional
    @OnClick(R.id.item_2_4)
    public void onItem24Clicked(View view) {
        onItemClicked((ImageView) view, 2, 4);
    }

    @Optional
    @OnClick(R.id.item_3_1)
    public void onItem31Clicked(View view) {
        onItemClicked((ImageView) view, 3, 1);
    }

    @Optional
    @OnClick(R.id.item_3_2)
    public void onItem32Clicked(View view) {
        onItemClicked((ImageView) view, 3, 2);
    }

    @Optional
    @OnClick(R.id.item_3_3)
    public void onItem33Clicked(View view) {
        onItemClicked((ImageView) view, 3, 3);
    }

    @Optional
    @OnClick(R.id.item_3_4)
    public void onItem34Clicked(View view) {
        onItemClicked((ImageView) view, 3, 4);
    }

    @Optional
    @OnClick(R.id.item_4_1)
    public void onItem41Clicked(View view) {
        onItemClicked((ImageView) view, 4, 1);
    }

    @Optional
    @OnClick(R.id.item_4_2)
    public void onItem42Clicked(View view) {
        onItemClicked((ImageView) view, 4, 2);
    }

    @Optional
    @OnClick(R.id.item_4_3)
    public void onItem43Clicked(View view) {
        onItemClicked((ImageView) view, 4, 3);
    }

    @Optional
    @OnClick(R.id.item_4_4)
    public void onItem44Clicked(View view) {
        onItemClicked((ImageView) view, 4, 4);
    }

    @Optional
    @OnClick(R.id.item_5_1)
    public void onItem51Clicked(View view) {
        onItemClicked((ImageView) view, 5, 1);
    }

    @Optional
    @OnClick(R.id.item_5_2)
    public void onItem52Clicked(View view) {
        onItemClicked((ImageView) view, 5, 2);
    }

    @Optional
    @OnClick(R.id.item_5_3)
    public void onItem53Clicked(View view) {
        onItemClicked((ImageView) view, 5, 3);
    }

    @Optional
    @OnClick(R.id.item_5_4)
    public void onItem54Clicked(View view) {
        onItemClicked((ImageView) view, 5, 4);
    }
}
