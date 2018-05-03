package com.leaflet.gameapp.presentation.ui.adapters;

/**
 * Created by yasser.ibrahim on 5/3/2018.
 */

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.leaflet.gameapp.R;

import co.ceryle.fitgridview.FitGridAdapter;

public class Adapter extends FitGridAdapter {

    private int[] drawables = {
            R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark,
            R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark,
            R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark,
            R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark,
            R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark, R.drawable.ic_question_mark};

        private int[] drawables2 = {
            R.drawable.face_1, R.drawable.face_2, R.drawable.face_3, R.drawable.face_4,
            R.drawable.face_5, R.drawable.face_6, R.drawable.face_7, R.drawable.face_8,
            R.drawable.face_9, R.drawable.face_10, R.drawable.face_11, R.drawable.face_12,
            R.drawable.face_5, R.drawable.face_6, R.drawable.face_7, R.drawable.face_8,
            R.drawable.face_9, R.drawable.face_10, R.drawable.face_11, R.drawable.face_12};

    private Context context;

    public Adapter(Context context) {
        super(context, R.layout.grid_item);
        this.context = context;
    }

    @Override
    public void onBindView(final int position, View itemView) {
        final ImageView image = itemView.findViewById(R.id.grid_item_iv);
        image.setImageResource(drawables[position]);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(drawables2[position]);
                animateImage(image, true);
            }
        });
    }

    private void animateImage(ImageView image, boolean isSelected) {
        AnimatorSet animator;
        if (isSelected) {
            animator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.in_animator);
        } else {
            animator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.out_animator);
        }
        animator.setTarget(image);
        animator.start();
    }
}