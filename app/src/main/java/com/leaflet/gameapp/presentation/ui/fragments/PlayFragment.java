package com.leaflet.gameapp.presentation.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.listeners.OnDimensionChanged;
import com.leaflet.gameapp.domain.utils.ConstantsIds;
import com.leaflet.gameapp.presentation.ui.activities.MainActivity;
import com.leaflet.gameapp.presentation.ui.adapters.Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.ceryle.fitgridview.FitGridView;

public class PlayFragment extends Fragment implements OnDimensionChanged {

    @BindView(R.id.gridView)
    FitGridView gridView;

    private OnFragmentInteractionListener listener;
    private int rows;
    private int columns;

    public PlayFragment() {
    }

    public static PlayFragment newInstance(int rows, int columns) {
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putInt(ConstantsIds.ROWS_COUNT_ID, rows);
        args.putInt(ConstantsIds.COLUMNS_COUNT_ID, columns);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rows = getArguments().getInt(ConstantsIds.ROWS_COUNT_ID);
            columns = getArguments().getInt(ConstantsIds.COLUMNS_COUNT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        ButterKnife.bind(this, view);

        gridView.setFitGridAdapter(new Adapter(getContext()));
        onDimensionChanges(rows, columns);

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
        if (context instanceof MainActivity) {
            ((MainActivity) context).setOnDimensionChanged(this);
        }

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
    public void onDimensionChanges(int rows, int columns) {
        gridView.setNumRows(rows);
        gridView.setNumColumns(columns);
        gridView.update();
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
}
